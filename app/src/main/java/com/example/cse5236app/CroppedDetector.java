package com.example.cse5236app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

import java.io.ByteArrayOutputStream;

public class CroppedDetector extends Detector {

    Detector mDelegate;

    public CroppedDetector(Detector delegate) {
        mDelegate = delegate;
    }

    @Override
    public SparseArray detect(Frame frame) {
        int width = frame.getMetadata().getWidth();
        int height = frame.getMetadata().getHeight();

        int right = (width / 2) + (width / 4);
        int left = (width / 2) - (width / 4);
        int bottom = (height / 2) + (height / 4);
        int top = (height / 2) - (height / 4);

        YuvImage yuvImage = new YuvImage(frame.getGrayscaleImageData().array(), ImageFormat.NV21, width, height, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(left, top, right, bottom), 50, byteArrayOutputStream);
        byte[] jpegArray = byteArrayOutputStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(jpegArray, 0, jpegArray.length);

        Frame croppedFrame = new Frame.Builder()
                .setBitmap(bitmap)
                .setRotation(frame.getMetadata().getRotation())
                .build();

        return mDelegate.detect(croppedFrame);
    }

    @Override
    public boolean isOperational() {
        return mDelegate.isOperational();
    }

    @Override
    public boolean setFocus(int id) {
        return mDelegate.setFocus(id);
    }

}

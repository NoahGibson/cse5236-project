package com.example.cse5236app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cse5236app.ui.CameraSourcePreview;
import com.example.cse5236app.ui.GraphicOverlay;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class TextScannerFragment extends Fragment {

    private static final String TAG = "TextScannerFragment";

    private static final int requestPermissionID = 2;

    private CameraSource mCameraSource;
    private CameraSourcePreview mCameraPreview;
    private GraphicOverlay<OcrGraphic> mGraphicOverlay;


    public TextScannerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_text_scanner, container, false);

        mCameraPreview = (CameraSourcePreview) v.findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay<OcrGraphic>) v.findViewById(R.id.graphicOverlay);

        int rc = ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
        } else {
            requestCameraPermission();
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        startCameraSource();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mCameraPreview != null) {
            mCameraPreview.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCameraPreview != null) {
            mCameraPreview.release();
        }
    }

    private void createCameraSource() {
        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();
        textRecognizer.setProcessor(new OcrDetectorProcessor(mGraphicOverlay));

//        final CroppedDetector croppedDetector = new CroppedDetector(textRecognizer);
//        croppedDetector.setProcessor(new OcrDetectorProcessor(mGraphicOverlay));

        if (!textRecognizer.isOperational()) {
            Log.w(TAG, "Detector dependencies not loaded yet");
        } else{
            mCameraSource = new CameraSource.Builder(getContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setAutoFocusEnabled(true)
                    .setRequestedFps(2.0f)
                    .build();
        }
    }

    private void startCameraSource() throws SecurityException {
        if (mCameraSource != null) {
            try {
                mCameraPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source");
                mCameraSource.release();
                mCameraSource = null;
            }
        }
    }

    private void requestCameraPermission() {
        Log.w(TAG, "Requesting camera permission");

        final String[] permissions = new String[]{ Manifest.permission.CAMERA };

        requestPermissions(permissions, requestPermissionID);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                          @NonNull String[] permissions,
                                          @NonNull int[] grantResults) {
        if (requestCode != requestPermissionID) {
            Log.d(TAG, "Received unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
            return;
        }

        Log.e(TAG, "Camera permission not granted");
    }

}

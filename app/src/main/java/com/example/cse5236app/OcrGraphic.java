package com.example.cse5236app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.cse5236app.ui.GraphicOverlay;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

import java.util.List;

public class OcrGraphic extends GraphicOverlay.Graphic {

    private int mId;

    private static final int TEXT_COLOR = Color.WHITE;

    private static Paint sRectPaint;
    private static Paint sTextPaint;
    private final TextBlock mText;

    OcrGraphic(GraphicOverlay overlay, TextBlock text) {
        super(overlay);

        mText = text;

        if (sRectPaint == null) {
            sRectPaint = new Paint();
            sRectPaint.setColor(TEXT_COLOR);
            sRectPaint.setStyle(Paint.Style.STROKE);
            sRectPaint.setStrokeWidth(4.0f);
        }

        if (sTextPaint == null) {
            sTextPaint = new Paint();
            sTextPaint.setColor(TEXT_COLOR);
            sTextPaint.setTextSize(54.0f);
        }

        postInvalidate();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public TextBlock getTextBlock() {
        return mText;
    }

    public boolean contains(float x, float y) {
        TextBlock text = mText;
        if (text == null) {
            return false;
        }
        RectF rect = new RectF(text.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        return (rect.left < x && rect.right > x && rect.top < y && rect.bottom > y);
    }

    @Override
    public void draw(Canvas canvas) {
        TextBlock text = mText;
        if (text == null) {
            return;
        }

        RectF rect = new RectF(text.getBoundingBox());
        rect.left = translateX(rect.left) + canvas.getWidth() / (float) 4;
        rect.top = translateY(rect.top) + canvas.getHeight() / (float) 4;
        rect.right = translateX(rect.right) + canvas.getWidth() / (float) 4;
        rect.bottom = translateY(rect.bottom) + canvas.getHeight() / (float) 4;
        canvas.drawRect(rect, sRectPaint);

        List<? extends Text> textComponents = text.getComponents();
        for (Text currentText : textComponents) {
            float left = translateX(currentText.getBoundingBox().left) + canvas.getWidth() / (float) 4;
            float bottom = translateY(currentText.getBoundingBox().bottom) + canvas.getHeight() / (float) 4;
            canvas.drawText(currentText.getValue(), left, bottom, sTextPaint);
        }
    }

}
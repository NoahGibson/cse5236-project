package com.example.cse5236app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cse5236app.R;

public class CameraPrompt extends View {

    private Paint rectPaint = new Paint();
    private Paint promptPaint = new Paint();

    CameraPrompt(Context context) {
        super(context);

        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.WHITE);
        rectPaint.setStrokeWidth(30);

        promptPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        promptPaint.setColor(Color.WHITE);
        promptPaint.setStrokeWidth(3);
        promptPaint.setTextSize(75.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x0 = getWidth() / 2;
        int y0 = getHeight() / 2;
        int dx = getWidth() / 4;
        int dy = getHeight() / 4;

        String prompt = getResources().getString(R.string.text_scanner_prompt);
        float promptWidth = promptPaint.measureText(prompt);

        canvas.drawText(prompt, x0 - promptWidth / 2, y0 - dy - 100, promptPaint);
        canvas.drawRect(x0 - dx, y0 - dy, x0 + dx, y0 + dy, rectPaint);
    }
}

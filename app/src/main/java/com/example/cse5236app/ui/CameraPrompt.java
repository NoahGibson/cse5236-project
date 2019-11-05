package com.example.cse5236app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cse5236app.R;

public class CameraPrompt extends View {

    private Paint paint = new Paint();

    CameraPrompt(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(30);

        int x0 = getWidth() / 2;
        int y0 = getHeight() / 2;
        int dx = getWidth() / 4;
        int dy = getHeight() / 4;

        canvas.drawText(getResources().getString(R.string.text_scanner_prompt), x0 - dx, y0 - dy - 50, paint);
        canvas.drawRect(x0 - dx, y0 - dy, x0 + dx, y0 + dy, paint);
    }
}

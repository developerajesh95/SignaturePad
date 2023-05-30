package com.pad.signature;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

class SignatureView extends View {

    private int backgroundColor = Color.WHITE;

    private final Paint paint = new Paint();
    private final Path path = new Path();

    private float lastTouchX = 0f;
    private float lastTouchY = 0f;

    public SignatureView(Context context) {
        super(context);
        setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        paint.setAntiAlias(true);
        int strokeColor = Color.BLACK;
        paint.setColor(strokeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        float strokeWidth = 10f;
        paint.setStrokeWidth(strokeWidth);
    }

    void setStrokeWidth(float strokeWidth) {
        paint.setStrokeWidth(strokeWidth);
    }

    void setStrokeColor(int strokeColor) {
        paint.setColor(strokeColor);
    }

    void setBackground(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            path.moveTo(event.getX(), event.getY());
            lastTouchX = event.getX();
            lastTouchY = event.getY();

            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_MOVE) {
            int historySize = event.getHistorySize();
            for (int i = 0; i < historySize; i++) {
                float historicalX = event.getHistoricalX(i);
                float historicalY = event.getHistoricalY(i);
                path.lineTo(historicalX, historicalY);
            }
            path.lineTo(event.getX(), event.getY());
        } else {
            return false;
        }
        invalidate();
        lastTouchX = event.getX();
        lastTouchY = event.getY();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(backgroundColor);
        canvas.drawPath(path, paint);
    }

    void clear(){
        path.reset();
        invalidate();
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }
}

package com.viewslider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PageIndicator extends View {

    private Paint mUnselectedDotPaint;
    private Paint mSelectedDotPaint;

    private float mDotRadius;
    private int mUnselectedDotColor;
    private int mSelectedDotColor;

    public PageIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initSelectedDotPaint();
        initUnselectedDotPain();
    }

    private void initUnselectedDotPain() {
        mUnselectedDotPaint = new Paint();
    }

    private void initSelectedDotPaint() {
        mSelectedDotPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCenterDot(canvas);
        drawStartDot(canvas);
        drawEndDot(canvas);
    }

    private void drawCenterDot(Canvas canvas) {

    }

    private void drawStartDot(Canvas canvas) {

    }

    private void drawEndDot(Canvas canvas) {

    }

    private float getCenterX() {
        return getWidth() / 2;
    }

    private float getCenterY() {
        return getHeight() / 2;
    }
}

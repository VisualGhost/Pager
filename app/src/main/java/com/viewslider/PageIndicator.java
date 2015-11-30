package com.viewslider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PageIndicator extends View implements DotIndicator {

    private Paint mUnselectedDotPaint;
    private Paint mSelectedDotPaint;

    private float mDotRadius;
    private float mDistanceBetweenDots;
    private int mUnselectedDotColor;
    private int mSelectedDotColor;

    private Dot mStartDot;
    private Dot mCenterDot;
    //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
    //private Dot mEndDot;

    private Flyweight mFlyweight;

    private State mState;
    private State mStartDotSelectedState;
    private State mCenterDotSelectedState;
    //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
    //private State mEndDotSelectedState;


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
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = context.obtainStyledAttributes(attrs, R.styleable.PageIndicator);
                mDotRadius = array.getDimension(R.styleable.PageIndicator_dotRaius, 0f);
                mDistanceBetweenDots = array.getDimension(R.styleable.PageIndicator_distanceBetweenDots, 0f);
                mUnselectedDotColor = array.getColor(R.styleable.PageIndicator_unSelectedDotColor, Color.BLACK);
                mSelectedDotColor = array.getColor(R.styleable.PageIndicator_selectedDotColor, Color.WHITE);
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }

        initSelectedDotPaint();
        initUnselectedDotPain();
        initFlyweight();
        initStates();
    }

    private void initDots() {
        if (mStartDot == null) {
            mStartDot = new Dot(mFlyweight, getCenterX() - mDotRadius - mDistanceBetweenDots / 2, getCenterY());
        }
        if (mCenterDot == null) {
            mCenterDot = new Dot(mFlyweight, getCenterX() + mDotRadius + mDistanceBetweenDots / 2, getCenterY());
        }

        //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
//        if (mStartDot == null) {
//            mStartDot = new Dot(mFlyweight, getCenterX() - 2 * mDotRadius - mDistanceBetweenDots, getCenterY());
//        }
//        if (mCenterDot == null) {
//            mCenterDot = new Dot(mFlyweight, getCenterX(), getCenterY());
//        }
//        if (mEndDot == null) {
//            mEndDot = new Dot(mFlyweight, getCenterX() + 2 * mDotRadius + mDistanceBetweenDots, getCenterY());
//        }
    }

    private void initFlyweight() {
        mFlyweight = new Flyweight();
        mFlyweight.distanceToNeighbor = mDistanceBetweenDots;
        mFlyweight.radius = mDotRadius;
    }

    private void initUnselectedDotPain() {
        mUnselectedDotPaint = new Paint();
        mUnselectedDotPaint.setAntiAlias(true);
        mUnselectedDotPaint.setColor(mUnselectedDotColor);
    }

    private void initSelectedDotPaint() {
        mSelectedDotPaint = new Paint();
        mSelectedDotPaint.setAntiAlias(true);
        mSelectedDotPaint.setColor(mSelectedDotColor);
    }

    private void initStates() {
        mStartDotSelectedState = new StartDotSelectedState();
        mCenterDotSelectedState = new CenterDotSelectedState();
        //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
        //mEndDotSelectedState = new EndDotSelectedState();
        mState = mStartDotSelectedState;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initDots();
        invalidateState();
        drawCenterDot(canvas);
        drawStartDot(canvas);
        drawEndDot(canvas);
    }

    private void invalidateState() {
        if (mState != null) {
            mState.invalidate();
        }
    }

    private void drawCenterDot(Canvas canvas) {
        mCenterDot.draw(canvas);
    }

    private void drawStartDot(Canvas canvas) {
        mStartDot.draw(canvas);
    }

    private void drawEndDot(Canvas canvas) {
        //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
        //mEndDot.draw(canvas);
    }

    private static class Flyweight {
        float radius;
        float distanceToNeighbor;
    }

    private static class Dot {

        Flyweight flyweight;
        float radius;
        float distanceToNeighbor;
        float x;
        float y;
        Paint paint;

        public Dot(Flyweight flyweight, float x, float y) {
            this.flyweight = flyweight;
            radius = flyweight.radius;
            distanceToNeighbor = flyweight.distanceToNeighbor;
            this.x = x;
            this.y = y;
        }

        void setPaint(Paint paint) {
            this.paint = paint;
        }

        void draw(Canvas canvas) {
            if (paint != null) {
                canvas.drawCircle(x, y, radius, paint);
            }
        }
    }

    private float getCenterX() {
        return getWidth() / 2;
    }

    private float getCenterY() {
        return getHeight() / 2;
    }

    private interface State {
        void invalidate();
    }

    private class StartDotSelectedState implements State {

        @Override
        public void invalidate() {
            if (mStartDot != null) {
                mStartDot.setPaint(mSelectedDotPaint);
            }
            if (mCenterDot != null) {
                mCenterDot.setPaint(mUnselectedDotPaint);
            }
            //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
//            if (mEndDot != null) {
//                mEndDot.setPaint(mUnselectedDotPaint);
//            }
        }
    }

    private class CenterDotSelectedState implements State {

        @Override
        public void invalidate() {
            if (mStartDot != null) {
                mStartDot.setPaint(mUnselectedDotPaint);
            }
            if (mCenterDot != null) {
                mCenterDot.setPaint(mSelectedDotPaint);
            }
            //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
//            if (mEndDot != null) {
//                mEndDot.setPaint(mUnselectedDotPaint);
//            }
        }
    }

    private class EndDotSelectedState implements State {

        @Override
        public void invalidate() {
            if (mStartDot != null) {
                mStartDot.setPaint(mUnselectedDotPaint);
            }
            if (mCenterDot != null) {
                mCenterDot.setPaint(mUnselectedDotPaint);
            }
            //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
//            if (mEndDot != null) {
//                mEndDot.setPaint(mSelectedDotPaint);
//            }
        }
    }

    @Override
    public void selectDot(int index) {
        switch (index) {
            case 0:
                setState(mStartDotSelectedState);
                break;
            case 1:
                setState(mCenterDotSelectedState);
                break;
            case 2:
                //TODO uncomment when there are three pages (STREAM, POSITIONS, PORTFOLIO)
                //setState(mEndDotSelectedState);
                break;
        }
        invalidate();
    }

    private void setState(State state) {
        mState = state;
    }
}

package com.cme.mm.testdemo.widgets.full_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Descriptions：仪表盘控件
 * <p>
 * Author：ChenME
 * Date：2016/9/26
 * Email：ibelieve1210@163.com
 */
public class ClockViewSelf extends View {

    private Paint mPaint;
//    private float mScreenWidth;
//    private float mScreenHeight;

    private float mViewWidth;
    private float mViewHeight;

    public ClockViewSelf(Context context) {
        this(context, null);
    }

    public ClockViewSelf(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ClockViewSelf(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        mScreenWidth = dm.widthPixels;
//        mScreenHeight = dm.heightPixels;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getWidth() / 2.0f;
        mViewHeight = getHeight() / 2.0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();

        //画外圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5.0f);
//        canvas.drawCircle(mScreenWidth/2.0f,mScreenHeight/2.0f,mScreenWidth/2.0f,mPaint);
        canvas.drawCircle(mViewWidth, mViewHeight, Math.min(mViewWidth, mViewHeight), mPaint);

        //画刻度
        for (int i = 0; i < 12; i++) {
            String text = i + "";
            if (i == 0 || i == 3 || i == 6 || i == 9) {
                mPaint.setStrokeWidth(5);
                canvas.drawLine(mViewWidth, mViewHeight - mViewWidth, mViewWidth, mViewHeight - mViewWidth + 30, mPaint);

                mPaint.setStrokeWidth(2.0f);
                mPaint.setTextSize(30.0f);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawText(text, mViewWidth - mPaint.measureText(text) / 2.0f, mViewHeight - mViewWidth + 60, mPaint);

            } else {
                mPaint.setStrokeWidth(3);
                canvas.drawLine(mViewWidth, mViewHeight - mViewWidth, mViewWidth, mViewHeight - mViewWidth + 15, mPaint);

                mPaint.setStrokeWidth(1.0f);
                mPaint.setTextSize(20.0f);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawText(text, mViewWidth - mPaint.measureText(text) / 2.0f, mViewHeight - mViewWidth + 40, mPaint);
            }
            canvas.rotate(30.0f, mViewWidth, mViewHeight);
        }
    }
}

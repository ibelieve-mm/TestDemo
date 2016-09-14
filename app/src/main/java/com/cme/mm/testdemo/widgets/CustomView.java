package com.cme.mm.testdemo.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Descriptions：该类列出了自定义控件中常被重写的方法
 * <p/>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 所以的绘制工作都在该方法处理
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     *回调该方法来进行测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSpec(widthMeasureSpec,100), measureSpec(heightMeasureSpec, 50));
    }

    /**
     * 从XML加载组件后回调
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 组件大小改变时回调
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 回调该方法来确定显示的位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 监听到触摸事件时回调
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private int measureSpec(int measureSpec ,int def) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {//精确模式，即为宽、高指定具体数值，如100dp，或者是match_parent
            result = specSize;
        } else {
            result = def;//当模式为其他两种时，给一个默认的大小
            if (specMode == MeasureSpec.AT_MOST) {//最大值模式，即为宽、高指定wrap_content
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}

package com.cme.mm.testdemo.widgets.full_custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Descriptions：可以拖拽的View
 * <p>
 * Author：ChenME
 * Date：2016/9/20
 * Email：ibelieve1210@163.com
 */
public class DragView extends View {

    private int lastX, lastY;//用来记录上一次手指的位置

    private Scroller mScroller;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        /**
         * Scroller的使用：第1步，初始化Scroller
         */
        mScroller = new Scroller(context);
    }

    /**
     * Scroller的使用：第2步，重写computeScroll()方法
     * <p>
     * 是Scroller类的核心，系统在绘制View的时候会在draw()方法中调用该方法，
     * 其实该方法就是使用的scrollTo()方法
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过绘制来不断调用computeScroll
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录触摸位置的坐标
                lastX = rawX;
                lastY = rawY;
                break;

            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;

                /**
                 * Way1：利用layout(int left, int top, int right, int bottom)方法对View的位置进行修改
                 *
                 * 在left、top、right、bottom的基础上加上偏移量
                 */
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);

                /**
                 * Way2：通过offsetLeftAndRight(int offset)和offsetTopAndBottom(int offset)方法对View的位置及逆行修改
                 */
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                /**
                 * Way3：通过改变LayoutParams来动态修改一个布局的位置参数，从而达到改变View位置的效果
                 *
                 * 需要注意通过getLayoutParams()方法获取LayoutParams时，需要根据父布局的类型来设置不同的类型；
                 * 例如，在本例子中DragView的父布局是LinearLayout，所以就可以使用LinearLayout.LayoutParams
                 */
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                /**
                 * Way4：通过ViewGroup.MarginLayoutParams来实现
                 *
                 * 该方法不需要了考虑父布局的类型
                 */
//                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                marginLayoutParams.leftMargin = getLeft() + offsetX;
//                marginLayoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(marginLayoutParams);

                /**
                 * Way5：通过scrollTo(int x, int y)和scrollBy(int offsetX, int offsetY)方法来实现
                 *
                 * 由于这两个方法是对内容进行操作的（如对于TextView，移动的是文字；而ImageView移动的是图片），
                 * 所以该处应该获取父布局进行scroll
                 */
                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                //重新设置初始位置
                lastX = rawX;
                lastY = rawY;
                break;

            /**
             * Scroller的使用：第3步
             *
             * Way6：调用Scroller的startScroll(int startX, int startY, int dx, int dy, int duration)/
             * startScroll(int startX, int startY, int dx, int dy)方法移动View
             */
            case MotionEvent.ACTION_UP:
                View parent = (View) getParent();
                mScroller.startScroll(parent.getScrollX(), parent.getScrollY(), -parent.getScrollX(), -parent.getScrollY(), 5 * 1000);
                invalidate();
                break;
        }
        return true;
    }
}

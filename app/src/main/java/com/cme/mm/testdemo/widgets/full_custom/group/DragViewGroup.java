package com.cme.mm.testdemo.widgets.full_custom.group;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Descriptions：利用ViewDragHelper实现侧滑菜单
 * <p>
 * Author：ChenME
 * Date：2016/9/21
 * Email：ibelieve1210@163.com
 */
public class DragViewGroup extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mMenuView, mMainView;

    public DragViewGroup(Context context) {
        this(context, null);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        /**
         * 使用ViewDragHelper：步骤1，初始化ViewDragHelper
         */
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                /**
                 * 使用ViewDragHelper：步骤5，处理回调，如果当前触摸的childView为mMainView时开始检测触摸事件
                 */
                return mMainView == child;
            }

            /**
             * 使用ViewDragHelper：步骤6，重写滑动方法——clampViewPositionVertical() 和 clampViewPositionHorizontal() ，
             * 分别对应垂直和水平方向上的滑动；默认返回为0，即不发生滑动；如果只重写其中一个，那么就只会实现该方向上的滑动
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }

            /**
             * 使用ViewDragHelper：步骤7，重写onViewReleased()方法，当手指离开 View 时，让其滑动回初始位置
             */
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                //手指抬起后缓慢移动到指定位置
                if (mMainView.getLeft() < 300) {
                    mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                    ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                } else {
                    mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                    ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                }
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                /**
                 * 这个事件在用户触摸到View后回调
                 */
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                /**
                 * 这个事件在拖拽状态改变时回调，比如idle，dragging
                 */
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                /**
                 * 这个事件在位置改变时回调，常用于滑动时更改scale进行缩放等效果
                 */
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    /**
     * 使用ViewDragHelper：步骤2，重写拦截事件，将事件传递给ViewDragHelper进行处理
     */
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    /**
     * 使用ViewDragHelper：步骤3，重写触摸事件，将事件传递给ViewDragHelper进行处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    /**
     * 使用ViewDragHelper：步骤4，重写computeScroll()方法
     */
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}

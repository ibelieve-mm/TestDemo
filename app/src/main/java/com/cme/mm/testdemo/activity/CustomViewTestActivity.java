package com.cme.mm.testdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.cme.mm.testdemo.App;
import com.cme.mm.testdemo.R;
import com.cme.mm.testdemo.widgets.complex.TopBar;
import com.cme.mm.testdemo.widgets.text_view.XTextView;
import com.cme.mm.testdemo.widgets.text_view.XTextView1;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Descriptions：自定义控件的测试Activity
 * <p/>
 * Author：ChenME
 * Date：2016/9/18
 * Email：ibelieve1210@163.com
 */
@EActivity(R.layout.activity_custom_view_test)
public class CustomViewTestActivity extends AppCompatActivity {

    @ViewById(R.id.topBar)
    TopBar topBar;
    @ViewById(R.id.xtv1)
    XTextView1 xtv1;
    @ViewById(R.id.view)
    XTextView view;

    @AfterViews
    void onPageStart() {
        setListener();

        int[] xtv1Location = new int[2];
        xtv1.getLocationOnScreen(xtv1Location);//获取整个屏幕的绝对坐标

        int[] xtvLocation = new int[2];
        view.getLocationInWindow(xtvLocation);//获取当前窗口的绝对坐标

        App.log("xtv1 Location：(" + xtv1Location[0] + "," + xtv1Location[1] + ")");
        App.log("view Location：(" + xtvLocation[0] + "," + xtvLocation[1] + ")");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        App.log("event RawX/Y "+  event.getRawX()+ "," +event.getRawY());//获取到的结果是触摸位置的在Android坐标系中的绝对位置
        App.log("event X/Y "+  event.getX()+ "," +event.getY());//获取到的结果是触摸位置的在Android坐标系中的绝对位置

        App.log("View X/Y "+  view.getX() + "," +view.getY());//获取到View左上角顶点距父控件左边/上边的距离
        App.log("View Left/Right/Top/Bottom "
                +  view.getLeft() + "," +view.getRight() //获取到View自身左边/右边距离父控件左边的距离，所以他俩的差值即为控件自身的宽度
                + "," +view.getTop() + "," +view.getBottom());//获取到View自身上边/下边距离父控件上边的距离，所以他俩的差值即为控件自身的宽度
        App.log("View Width/Height "+  view.getWidth() + "," +view.getHeight());//获取控件自身的宽度/高度
        return super.onTouchEvent(event);
    }

    private void setListener() {
        topBar.setTopbarClickListener(new TopBar.TopbarClickListener() {
            @Override
            public void leftBtnClick() {
                App.toast("点击了左边的按钮");
            }

            @Override
            public void rightBtnClick() {
                App.toast("右边的按钮被点击了~~");
            }
        });

    }
}

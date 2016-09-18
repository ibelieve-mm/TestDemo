package com.cme.mm.testdemo.activity;

import android.support.v7.app.AppCompatActivity;

import com.cme.mm.testdemo.App;
import com.cme.mm.testdemo.R;
import com.cme.mm.testdemo.widgets.complex.TopBar;

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

    @AfterViews
    void onPageStart() {
        setListener();
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

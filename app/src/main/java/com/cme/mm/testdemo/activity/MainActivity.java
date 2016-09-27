package com.cme.mm.testdemo.activity;

import android.support.v7.app.AppCompatActivity;

import com.cme.mm.testdemo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @AfterViews
    void onPageStart() {
    }

    @Click(R.id.tv_toCustomView)
    void toCustomViewPage() {
        CustomViewTestActivity_.intent(this).start();
    }

    @Click(R.id.tv_toCustomScrollView)
    void toCustomScrollViewPage() {
        CustomScrollViewTestActivity_.intent(this).start();
    }

    @Click(R.id.tv_toDragView)
    void toDragViewPage() {
        DragViewTestActivity_.intent(this).start();
    }

    @Click(R.id.tv_toDragViewGroup)
    void toDragViewGroupPage() {
        DragViewGroupTestActivity_.intent(this).start();
    }

    @Click(R.id.tv_toChangeImgHueSaturationLumActivity)
    void oChangeImgHueSaturationLumPage() {
        ChangImgHueSaturationLumActivity_.intent(this).start();
    }
}

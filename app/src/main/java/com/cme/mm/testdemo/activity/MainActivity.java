package com.cme.mm.testdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cme.mm.testdemo.App;
import com.cme.mm.testdemo.R;
import com.cme.mm.testdemo.widgets.complex.TopBar;

/**
 * Descriptions：
 * <p/>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
public class MainActivity extends AppCompatActivity {

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topBar = (TopBar) findViewById(R.id.topBar);
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

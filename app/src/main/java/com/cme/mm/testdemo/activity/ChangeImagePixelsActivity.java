package com.cme.mm.testdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.cme.mm.testdemo.R;
import com.cme.mm.testdemo.utils.ImageHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Descriptions：图像像素点处理效果Activity
 * <p>
 * Author：ChenME
 * Date：2016/9/29
 * Email：ibelieve1210@163.com
 */

@EActivity(R.layout.activity_change_iamge_pixels)
public class ChangeImagePixelsActivity extends AppCompatActivity {

    @Bean(ImageHelper.class)
    ImageHelper imageHelper;

    @ViewById(R.id.iv_InuYasha)
    ImageView iv_InuYasha;

    private Bitmap bitMap;

    @AfterViews
    void onPageStart() {
        bitMap = BitmapFactory.decodeResource(getResources(), R.mipmap.inuyasha);
        iv_InuYasha.setImageBitmap(bitMap);
    }

    @Click(R.id.btn_negative)
    void toNegative() {
        iv_InuYasha.setImageBitmap(imageHelper.changeImagePixels(bitMap, 1));
    }

    @Click(R.id.btn_oldImage)
    void toOldImage() {
        iv_InuYasha.setImageBitmap(imageHelper.changeImagePixels(bitMap, 2));
    }

    @Click(R.id.btn_emboss)
    void toEmboss() {
        iv_InuYasha.setImageBitmap(imageHelper.changeImagePixels(bitMap, 3));
    }
}

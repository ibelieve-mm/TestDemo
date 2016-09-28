package com.cme.mm.testdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.cme.mm.testdemo.R;
import com.cme.mm.testdemo.utils.ImageHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SeekBarProgressChange;
import org.androidannotations.annotations.ViewById;

/**
 * Descriptions：改变图片的色调、饱和度以及亮度
 * <p>
 * Author：ChenME
 * Date：2016/9/27
 * Email：ibelieve1210@163.com
 */
@EActivity(R.layout.activity_change_img_hue_saturation_lum)
public class ChangImgHueSaturationLumActivity extends AppCompatActivity {

    @Bean(ImageHelper.class)
    ImageHelper imageHelper;

    @ViewById(R.id.iv_InuYasha)
    ImageView iv_InuYasha;
    @ViewById(R.id.sb_hue)
    SeekBar sb_hue;
    @ViewById(R.id.sb_saturation)
    SeekBar sb_saturation;
    @ViewById(R.id.sb_lum)
    SeekBar sn_lum;

    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    private float mHue, mSaturation, mLum;
    private Bitmap bitMap;

    @AfterViews
    void onPageStart() {
        bitMap = BitmapFactory.decodeResource(getResources(), R.mipmap.inuyasha);
        initView();
    }

    private void initView() {
        sb_hue.setMax(MAX_VALUE);
        sb_hue.setProgress(MID_VALUE);
        sb_saturation.setMax(MAX_VALUE);
        sb_saturation.setProgress(MID_VALUE);
        sn_lum.setMax(MAX_VALUE);
        sn_lum.setProgress(MID_VALUE);

        iv_InuYasha.setImageBitmap(bitMap);
    }

    @SeekBarProgressChange(R.id.sb_hue)
    void hueChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
//        iv_InuYasha.setImageBitmap(imageHelper.changeImgHueOnly(bitMap, mHue));//只改变色调
        iv_InuYasha.setImageBitmap(imageHelper.changeImgHueSaturationLum(bitMap, mHue, mSaturation, mLum));
    }

    @SeekBarProgressChange(R.id.sb_saturation)
    void saturationChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSaturation = progress * 1.0f / MID_VALUE;
//        iv_InuYasha.setImageBitmap(imageHelper.changImgSaturationOnly(bitMap, mSaturation));//只改变饱和度
        iv_InuYasha.setImageBitmap(imageHelper.changeImgHueSaturationLum(bitMap, mHue, mSaturation, mLum));
    }

    @SeekBarProgressChange(R.id.sb_lum)
    void lumChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mLum = progress * 1.0f / MID_VALUE;
//        iv_InuYasha.setImageBitmap(imageHelper.changImgLumOnly(bitMap, mLum));//只改变亮度
        iv_InuYasha.setImageBitmap(imageHelper.changeImgHueSaturationLum(bitMap, mHue, mSaturation, mLum));
    }
}
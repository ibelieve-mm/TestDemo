package com.cme.mm.testdemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import org.androidannotations.annotations.EBean;

/**
 * Descriptions：图片工具类
 * <p>
 * Author：ChenME
 * Date：2016/9/27
 * Email：ibelieve1210@163.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class ImageHelper {

    /**
     * 单独改变图片的色调
     *
     * @param bitmap 图片
     * @param hue    色调
     * @return
     */
    public Bitmap changeImgHueOnly(Bitmap bitmap, float hue) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

//        ColorMatrix imageMatrix = new ColorMatrix();
//        imageMatrix.postConcat(hueMatrix);
//        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        paint.setColorFilter(new ColorMatrixColorFilter(hueMatrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);

        return bmp;
    }

    /**
     * 单独改变图片的饱和度
     *
     * @param bitmap     图片
     * @param saturation 饱和度
     * @return
     */
    public Bitmap changImgSaturationOnly(Bitmap bitmap, float saturation) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        paint.setColorFilter(new ColorMatrixColorFilter(saturationMatrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }

    /**
     * 单独改变图片的亮度
     *
     * @param bitmap 图片
     * @param lum    亮度
     * @return
     */
    public Bitmap changImgLumOnly(Bitmap bitmap, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        paint.setColorFilter(new ColorMatrixColorFilter(lumMatrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }

    /**
     * 同时改变图片的色调、饱和度以及亮度
     *
     * @param bitmap     图片
     * @param hue        色调
     * @param saturation 饱和度
     * @param lum        亮度
     * @return
     */
    public Bitmap changeImgHueSaturationLum(Bitmap bitmap, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }


}

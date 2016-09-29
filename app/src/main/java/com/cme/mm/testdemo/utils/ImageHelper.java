package com.cme.mm.testdemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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

    /**
     * 图像像素处理特效
     *
     * @param bm   图片
     * @param flag 1：底片效果；2：老照片效果；3：浮雕效果
     * @return
     */
    public Bitmap changeImagePixels(Bitmap bm, int flag) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        int r, g, b, a;

        int colorBefore;
        int r1, g1, b1, a1;

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);

        int startIndex = 0;
        if (3 == flag) {
            startIndex = 1;
        }

        for (int i = startIndex; i < width * height; i++) {

            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            if (1 == flag) {
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
            } else if (2 == flag) {
                r = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                g = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                b = (int) (0.272 * r + 0.534 * g + 0.131 * b);
            } else if (3 == flag) {
                colorBefore = oldPx[i - 1];
                r1 = Color.red(colorBefore);
                g1 = Color.green(colorBefore);
                b1 = Color.blue(colorBefore);

                r = (r1 - r + 127);
                g = (g1 - g + 127);
                b = (b1 - b + 127);
            }

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }
            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }
            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
}

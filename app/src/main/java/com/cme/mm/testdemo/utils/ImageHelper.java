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

    public Bitmap changeImgHue(Bitmap bitmap, float hue) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2,hue);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);

        return bmp;
    }
}

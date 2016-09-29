package com.cme.mm.testdemo.widgets.full_custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.cme.mm.testdemo.R;

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：2016/9/29
 * Email：ibelieve1210@163.com
 */
public class ImagePositionMatrixView extends View{

    private Matrix mMatrix;
    private Bitmap mBitmap;

    public ImagePositionMatrixView(Context context) {
        this(context,null);
    }

    public ImagePositionMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImagePositionMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.inuyasha);
        setImageAndMatrix(bitmap, new Matrix());
    }


    public void setImageAndMatrix(Bitmap bm, Matrix matrix) {
        mMatrix = matrix;
        mBitmap = bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }
}

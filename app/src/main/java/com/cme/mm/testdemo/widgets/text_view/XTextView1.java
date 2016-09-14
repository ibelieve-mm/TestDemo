package com.cme.mm.testdemo.widgets.text_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Descriptions：闪动的TextView，类似于iOS中锁屏界面中“滑动来解锁”的效果
 * <p/>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
public class XTextView1 extends TextView {
    public XTextView1(Context context) {
        super(context);
    }

    public XTextView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XTextView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float mViewWidth;
    private float mTranslate;
    private Paint mPaint;
    private LinearGradient mLinearGradient;//线性梯度
    private Matrix mGradientMatrix;//矩阵

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //该段代码中最重要的是使用getPaint()获取当前绘制TextView的Paint对象,并且给这个Paint对象设置原生TextView没有的LinearGradient属性
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[]{Color.BLUE, 0xffffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != mGradientMatrix) {
            mTranslate += mViewWidth / 10;
            if (mTranslate > mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}

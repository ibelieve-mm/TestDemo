package com.cme.mm.testdemo.widgets.full_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Descriptions：模仿音乐播放器中音频条形图
 * <p/>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
public class AudioBarView extends View {

    private Paint paint = new Paint();
    private int mRectCount = 20;//竖条个数
    private float mWidth;//控件的宽度
    private float mHeight;//控件的高度
    private float mRectWidth;//每个竖条的宽度
    private float offsetPercent = 0.3f;//偏移量占竖条宽度的百分比
    private float offset;//偏移量，即每两个竖条的间隔

    public AudioBarView(Context context) {
        this(context, null);
    }

    public AudioBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRectWidth = mWidth / ((1 + offsetPercent) * mRectCount - offsetPercent);
        offset = mRectWidth * offsetPercent;
        LinearGradient mLinearGradient = new LinearGradient(0, 0, mWidth, mHeight, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            float currentHeight = (float) (Math.random() * mHeight);
            canvas.drawRect(mRectWidth * i + offset * i, currentHeight, mRectWidth * (i + 1) + offset * i, mHeight, paint);
        }
        postInvalidateDelayed(300);
    }
}
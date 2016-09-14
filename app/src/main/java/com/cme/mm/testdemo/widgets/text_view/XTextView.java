package com.cme.mm.testdemo.widgets.text_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 为TextView添加两个矩形背景
 * Created by Administrator on 2016/9/12.
 */
public class XTextView extends TextView {
    public XTextView(Context context) {
        super(context);
    }

    public XTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //在系统绘制文字之前先绘制两个矩形
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.MAGENTA);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);//绘制内边框

        mPaint.setColor(Color.LTGRAY);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint);//绘制内边框

        canvas.save();//对画布进行操作前,要保存状态

        //绘制文字前平移10像素
//        canvas.translate(10,0);

        super.onDraw(canvas);

        canvas.restore();//最后恢复画布
    }
}

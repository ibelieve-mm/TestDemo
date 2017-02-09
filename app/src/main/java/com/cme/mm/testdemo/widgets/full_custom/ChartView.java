package com.cme.mm.testdemo.widgets.full_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;


import com.cme.mm.testdemo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Descriptions：模仿音乐播放器中音频条形图
 * <p/>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
public class ChartView extends View {

    /**
     * 30天的数组
     */
    private List<Float> list;
    /**
     * 最大值
     */
    private float maxValue;

    /**
     * 画笔
     */
    private Paint paint;
    private Paint paintText;

    /**
     * 竖条个数
     */
    private int mRectCount = 30;


    /**
     * 控件的宽度
     */
    private float mWidth;

    /**
     * 控件的高度
     */
    private float mHeight;

    /**
     * chart宽度
     */
    private float chartWidth;

    /**
     * chart高度
     */
    private float chartHeight;

    /**
     * 每个竖条的宽度
     */
    private float mRectWidth;

    /**
     * 偏移量占竖条宽度的百分比
     */
    private float offsetPercent = 0.3f;

    /**
     * 偏移量，即每两个竖条的间隔
     */
    private float offset;

    /**
     * 竖条与圆点之间的间隔
     */
    private float chartAndTextSpace;

    /**
     * 圆点的直径
     */
    private float pointR;

    /**
     * 文字的大小
     */
    private float textSize;

    /**
     * chart左右的内边距
     */
    private float leftAndRightPadding;

    /**
     * 开始的日期
     */
    private String startDate;

    /**
     * 结束的日期
     */
    private String endDate;

    public ChartView(Context context) {
        this(context, null);
        init(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        chartAndTextSpace = context.getResources().getDimensionPixelOffset(R.dimen.size_chartAndTextSpace);
        textSize = context.getResources().getDimensionPixelOffset(R.dimen.size_text);
        leftAndRightPadding = context.getResources().getDimensionPixelOffset(R.dimen.size_padding);
        list = new ArrayList<>();

        SimpleDateFormat mFormatterTime = new SimpleDateFormat("M/d");
        Date date = new Date(System.currentTimeMillis());
        endDate = mFormatterTime.format(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -29);

        startDate = (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        paint = new Paint();

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextSize(textSize);
        paintText.setColor(Color.RED);
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 设置当前的值
     * @param arr
     */
    public void setCurrentValue(List<Float> arr) {
        list.clear();
        list.addAll(arr);
        if (arr.size() < 30) {
            for (int i = 0; i < 30 - arr.size(); i++) {
                list.add(0, 0f);
            }
        }
        invalidate();
    }

    /**
     * 设置最大值
     * @param maxValue
     */
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 获取字体的高度
     *
     * @return
     */
    private int getFontHeight() {
        Paint.FontMetrics fm = paintText.getFontMetrics();
        return (int) (fm.descent - fm.ascent);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        chartWidth = mWidth - 2 * leftAndRightPadding;
        mHeight = getMeasuredHeight();
        mRectWidth = chartWidth / ((1 + offsetPercent) * mRectCount - offsetPercent);
        pointR = mRectWidth / 2;
        chartHeight = mHeight - 3 * chartAndTextSpace - pointR - getFontHeight();
        offset = mRectWidth * offsetPercent;
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < list.size(); i++) {
            float temp = list.get(i) > maxValue ? maxValue : list.get(i);
            float currentHeight = temp * chartHeight / maxValue;

            float rectFStartX = mRectWidth * i + offset * i;
            float rectFEndX = mRectWidth * (i + 1) + offset * i;

            rectFStartX += leftAndRightPadding;
            rectFEndX += leftAndRightPadding;

            RectF rectF = new RectF(rectFStartX, chartHeight - currentHeight, rectFEndX, chartHeight);

            LinearGradient mLinearGradient = new LinearGradient(rectFStartX, chartHeight, rectFEndX,
                    chartHeight - currentHeight, Color.parseColor("#346B43"),
                    Color.parseColor("#39B44A"), Shader.TileMode.CLAMP);
            paint.setShader(mLinearGradient);
            canvas.drawRoundRect(rectF, mRectWidth / 2, mRectWidth / 2, paint);
            float tempPointR = pointR / 2;
            canvas.drawCircle(rectFStartX + tempPointR * 2, chartHeight + chartAndTextSpace + tempPointR, tempPointR, paint);


        }
        float textX = leftAndRightPadding + mRectWidth / 2;
        float textY = getFontHeight() + chartHeight + pointR + chartAndTextSpace;
        canvas.drawText(startDate, textX, textY, paintText);
        canvas.drawText(endDate, mWidth - textX, textY, paintText);
    }
}
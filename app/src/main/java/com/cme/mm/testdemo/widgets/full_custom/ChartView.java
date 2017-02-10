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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Descriptions：柱状图
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
    private float maxValue = 1000;

    /**
     * 画笔
     */
    private Paint paint;
    private Paint paintTextRuler;
    private Paint paintText;

    /**
     * 竖条个数(显示30天的数据，所以为30)
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
     * 左侧标尺右侧的X坐标
     */
    float rulerTextRightX;

    /**
     * 左侧标尺文字的宽度
     */
    private float leftRulerTextWidth;

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
        list = new ArrayList<>();
        chartAndTextSpace = context.getResources().getDimensionPixelOffset(R.dimen.size_chartAndTextSpace);
        textSize = context.getResources().getDimensionPixelOffset(R.dimen.size_text);
        leftAndRightPadding = context.getResources().getDimensionPixelOffset(R.dimen.size_padding);
        leftRulerTextWidth = context.getResources().getDimensionPixelOffset(R.dimen.size_leftRulerTextWidth);

        rulerTextRightX = leftRulerTextWidth + leftAndRightPadding;

        setDemoChartDataList();

        SimpleDateFormat mFormatterTime = new SimpleDateFormat("M/d");
        Date date = new Date(System.currentTimeMillis());
        endDate = mFormatterTime.format(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -29);
        startDate = (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        paint = new Paint();

        paintTextRuler = new Paint();
        paintTextRuler.setAntiAlias(true);
        paintTextRuler.setTextSize(textSize);
        paintTextRuler.setColor(Color.RED);
        paintTextRuler.setTextAlign(Paint.Align.LEFT);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextSize(textSize);
        paintText.setColor(Color.RED);
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 设置当前的值
     *
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
     *
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
    private int getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) (fm.descent - fm.ascent);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        chartWidth = mWidth - rulerTextRightX - leftAndRightPadding;
        mRectWidth = chartWidth / ((1 + offsetPercent) * mRectCount - offsetPercent);
        pointR = mRectWidth / 2;
        mHeight = getMeasuredHeight();

        chartHeight = mHeight - 3 * chartAndTextSpace - pointR ;
        offset = mRectWidth * offsetPercent;
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float rulerTextHeight = getFontHeight(paintTextRuler);

        //重新计算chart的高度（减去标尺文字的高度）
        chartHeight -= rulerTextHeight;
        float chartBottom = rulerTextHeight + chartHeight;

        for (int i = 0; i < list.size(); i++) {
            float temp = list.get(i) > maxValue ? maxValue : list.get(i);
            float currentHeight = temp * chartHeight / maxValue;

            float rectFStartX = mRectWidth * i + offset * i;
            float rectFEndX = mRectWidth * (i + 1) + offset * i;

            rectFStartX += rulerTextRightX;
            rectFEndX += rulerTextRightX;

            RectF rectF = new RectF(rectFStartX, chartHeight - currentHeight + rulerTextHeight, rectFEndX, chartBottom);

            LinearGradient mLinearGradient = new LinearGradient(rectFStartX, chartHeight - currentHeight + rulerTextHeight, rectFEndX,
                    chartBottom, Color.parseColor("#39B44A"), Color.parseColor("#346B43"), Shader.TileMode.CLAMP);
            paint.setShader(mLinearGradient);
            canvas.drawRoundRect(rectF, mRectWidth / 2, mRectWidth / 2, paint);
            float tempPointR = pointR / 2;
            canvas.drawCircle(rectFStartX + tempPointR * 2, chartBottom + chartAndTextSpace + tempPointR, tempPointR, paint);
        }

        float textXLeft = leftAndRightPadding + leftRulerTextWidth + mRectWidth / 2;
        float textXRight = mWidth - (leftAndRightPadding + mRectWidth / 2);
        float textY = getFontHeight(paintText) + chartBottom + pointR + chartAndTextSpace;
        canvas.drawText(startDate, textXLeft, textY, paintText);
        canvas.drawText(endDate, textXRight, textY, paintText);

        float maxValueKilo = maxValue/1000;
        canvas.drawText(formatNumber(maxValueKilo), leftAndRightPadding, rulerTextHeight, paintTextRuler);
        canvas.drawText(formatNumber(maxValueKilo * 0.75f), leftAndRightPadding, chartHeight * 0.25f + rulerTextHeight, paintTextRuler);
        canvas.drawText(formatNumber(maxValueKilo * 0.5f), leftAndRightPadding, chartHeight * 0.5f + rulerTextHeight, paintTextRuler);
        canvas.drawText(formatNumber(maxValueKilo * 0.25f), leftAndRightPadding, chartHeight * 0.75f + rulerTextHeight, paintTextRuler);
        canvas.drawText("   0", leftAndRightPadding, chartHeight + rulerTextHeight, paintTextRuler);
    }


    private void setDemoChartDataList() {
        list.add(100f);
        list.add(12f);
        list.add(14f);
        list.add(90f);
        list.add(44f);
        list.add(55f);
        list.add(50f);
        list.add(100f);
        list.add(23f);
        list.add(76f);
        list.add(78f);
        list.add(66f);
        list.add(89f);
        list.add(88f);
        list.add(53f);
        list.add(65f);
        list.add(90f);
        list.add(99f);
        list.add(76f);
        list.add(77f);
        list.add(0f);
        list.add(2f);
        list.add(5f);
        list.add(78f);
        list.add(99f);
        list.add(1f);
        list.add(2f);
        list.add(0f);
        list.add(100f);
        list.add(99f);
    }

    /**
     * 保留固定位数的double数字
     *
     * @return
     */
    private String formatNumber(float num) {
        String format = "0.00";
        if (num >= 10) {
            format = "0.0";
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(num) + "k";
    }
}
package com.cme.mm.testdemo.widgets.full_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：2016/9/26
 * Email：ibelieve1210@163.com
 */
public class LayerTestView extends View {

    private Paint mPaint ;

    public LayerTestView(Context context) {
        this(context,null);
    }

    public LayerTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayerTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();
//        canvas.drawColor(Color.GREEN);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150,150,100,mPaint);

        canvas.saveLayerAlpha(0,0,400,400,127,LAYER_TYPE_NONE);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200,150,100,mPaint);
        canvas.restore();

        canvas.saveLayerAlpha(0,0,400,400,127,LAYER_TYPE_NONE);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(175,175,100,mPaint);
        canvas.restore();
    }
}

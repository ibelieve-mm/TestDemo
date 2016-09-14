package com.cme.mm.testdemo.widgets.complex;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cme.mm.testdemo.R;

/**
 * Descriptions：自定义的标题栏，中间是标题，左右两边是两个按钮
 * <p/>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
public class TopBar extends RelativeLayout {
    private Context mContext;

    //控件中一些默认的值
    private final int DEFAULT_TITLE_TEXT_COLOR = Color.parseColor("#333333");
    private final int DEFAULT_LEFT_RIGHT_TEXT_COLOR = Color.parseColor("#666666");
    private final float DEFAULT_TITLE_TEXT_SIZE = 16f;
    private final float DEFAULT_LEFT_RIGHT_TEXT_SIZE = 12f;

    //控件中保存参数的变量
    private String mTitle;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private float mLeftRightTextSize;
    private String mLeftText;
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mRightText;
    private int mRightTextColor;
    private Drawable mRightBackground;

    //组合View的各组成部分
    private TextView tv_leftBtn;
    private TextView tv_rightBtn;
    private TextView tv_title;

    private TopbarClickListener mTopbarClickListener;


    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        //将attrs中定义的declare-styleable的所有属性的值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        //从TypedArray中取出对应的值来为要设置的属性赋值
        mTitle = ta.getString(R.styleable.TopBar_titleTextTB);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSizeTB, DEFAULT_TITLE_TEXT_SIZE);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColorTB, DEFAULT_TITLE_TEXT_COLOR);

        mLeftRightTextSize = ta.getDimension(R.styleable.TopBar_leftRightTextSize, DEFAULT_LEFT_RIGHT_TEXT_SIZE);

        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, DEFAULT_LEFT_RIGHT_TEXT_COLOR);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);

        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, DEFAULT_LEFT_RIGHT_TEXT_COLOR);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);

        //获取完TypedArray的值后，要调用recycle()方法来避免重新创建的时候出现错误
        ta.recycle();

        addChildrenView();
    }

    private void addChildrenView() {
        tv_leftBtn = new TextView(mContext);
        tv_rightBtn = new TextView(mContext);
        tv_title = new TextView(mContext);

        tv_leftBtn.setText(mLeftText);
        tv_leftBtn.setTextColor(mLeftTextColor);
        tv_leftBtn.setTextSize(mLeftRightTextSize);
        tv_leftBtn.setBackground(mLeftBackground);

        tv_rightBtn.setText(mRightText);
        tv_rightBtn.setTextColor(mRightTextColor);
        tv_rightBtn.setTextSize(mLeftRightTextSize);
        tv_rightBtn.setBackground(mRightBackground);

        tv_title.setText(mTitle);
        tv_title.setTextColor(mTitleTextColor);
        tv_title.setTextSize(mTitleTextSize);
        tv_title.setGravity(Gravity.CENTER);


        LayoutParams mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        tv_leftBtn.setGravity(Gravity.CENTER);
        tv_leftBtn.setPadding(10, 0, 0, 0);
        addView(tv_leftBtn, mLeftParams);

        LayoutParams mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        tv_rightBtn.setGravity(Gravity.CENTER);
        tv_rightBtn.setPadding(0, 0, 10, 0);
        addView(tv_rightBtn, mRightParams);

        LayoutParams mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(tv_title, mTitleParams);


        tv_leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mTopbarClickListener) {
                    mTopbarClickListener.leftBtnClick();
                }
            }
        });

        tv_rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mTopbarClickListener) {
                    mTopbarClickListener.rightBtnClick();
                }
            }
        });
    }

    public void setTopbarClickListener(TopbarClickListener mTopbarClickListener) {
        this.mTopbarClickListener = mTopbarClickListener;
    }

    public interface TopbarClickListener {
        void leftBtnClick();

        void rightBtnClick();
    }
}
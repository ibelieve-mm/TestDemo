package com.cme.mm.testdemo.utils;

import android.content.Context;
import android.util.TypedValue;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Descriptions：与显示相关的工具类
 * <p>
 * Author：ChenME
 * Date：2016/9/23
 * Email：ibelieve1210@163.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class DisplayUtil {

    @RootContext
    Context mContext;

    /**
     * 将px转化为dp
     *
     * @param pxValue px
     * @return dp
     */
    public int px2dp(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dp转化为px
     *
     * @param dpValue dp
     * @return px
     */
    public int dp2px(float dpValue) {
//        final float scale = mContext.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, mContext.getResources().getDisplayMetrics());
    }

    /**
     * 将px转化为sp
     *
     * @param pxValue
     * @return
     */
    public int px2sp(float pxValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp转化为px
     *
     * @param spValue sp
     * @return px
     */
    public int sp2px(float spValue) {
//        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (spValue * fontScale + 0.5f);

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, mContext.getResources().getDisplayMetrics());
    }
}

package com.cme.mm.testdemo.constant;

/**
 * Descriptions：图片位置矩阵常量
 * <p>
 * Author：ChenME
 * Date：2016/9/29
 * Email：ibelieve1210@163.com
 */
public class ImgPositionMatrixConstant {
    /**
     * 矩阵
     * |A,B,C| X轴
     * |D,E,F| Y轴
     * |G,H,I|
     *
     * A,E控制Scale，即缩放变换
     * B,D控制Skew，即错切变换
     * C,F控制Trans，即平移变换
     *
     * A,B,D,E共同控制Rotate，即旋转变换
     * 例如顺时针旋转β
     * |cos(β), -sin(β), 0|
     * |sin(β), cos(β) , 0|
     * |0     , 0      , 1|
     */

    /**
     * 正常效果矩阵
     */
    public static final float[] POSITION_MATRIX_NOEMAL = {
            1, 0, 0,
            0, 1, 0,
            0, 0, 1,
    };

    /**
     * 向正方向平移（X轴100px，Y轴50px）
     */
    public static final float[] POSITION_MATRIX_TRANS = {
            1, 0, 100,
            0, 1, 50,
            0, 0, 1,
    };

    /**
     * 缩放（X轴2倍，Y轴3倍）
     */
    public static final float[] POSITION_MATRIX_SCALE = {
            2, 0, 0,
            0, 3, 0,
            0, 0, 1,
    };

    /**
     * 错切（X轴2倍）
     */
    public static final float[] POSITION_MATRIX_SKEW = {
            1, 2, 0,
            0, 1, 0,
            0, 0, 1,
    };

    /**
     * 旋转（顺时针旋转30°）
     */
    public static final float[] POSITION_MATRIX_ROTATE = {
            0.866f, -0.5f, 0,
            0.5f, 0.866f, 0,
            0, 0, 1,
    };
}
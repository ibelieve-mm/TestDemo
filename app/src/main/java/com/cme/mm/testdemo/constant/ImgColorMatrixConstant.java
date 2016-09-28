package com.cme.mm.testdemo.constant;

/**
 * Descriptions：图片特效矩阵常量
 * <p>
 * Author：ChenME
 * Date：2016/9/28
 * Email：ibelieve1210@163.com
 */
public class ImgColorMatrixConstant {

    /**
     * 正常效果矩阵
     */
    public static final float[] COLOR_MATRIX_NOEMAL = {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0
    };

    /**
     * 灰度效果矩阵
     */
    public static final float[] COLOR_MATRIX_GRAY_LEVEL_IMAGE = {
            0.33f, 0.59f, 0.11f, 0, 0,
            0.33f, 0.59f, 0.11f, 0, 0,
            0.33f, 0.59f, 0.11f, 0, 0,
            0, 0, 0, 1, 0
    };

    /**
     * 图像反转效果矩阵
     */
    public static final float[] COLOR_MATRIX_REVERSE_IMAGE = {
            -1, 0, 0, 1, 1,
            0, -1, 0, 1, 1,
            0, 0, -1, 1, 1,
            0, 0, 0, 1, 0
    };

    /**
     * 怀旧效果矩阵
     */
    public static final float[] COLOR_MATRIX_REMINISCENCE = {
            0.393f, 0.769f, 0.189f, 0, 0,
            0.349f, 0.686f, 0.168f, 0, 0,
            0.272f, 0.534f, 0.131f, 0, 0,
            0, 0, 0, 1, 0
    };

    /**
     * 去色效果矩阵
     */
    public static final float[] COLOR_MATRIX_DISCOLORATION = {
            1.5f, 1.5f, 1.5f, 0, -1,
            1.5f, 1.5f, 1.5f, 0, -1,
            1.5f, 1.5f, 1.5f, 0, -1,
            0, 0, 0, 1, 0
    };

    /**
     * 高饱和度效果矩阵
     */
    public static final float[] COLOR_MATRIX_HIGH_SATURATION= {
            1.438f, -0.122f, -0.016f, 0, -0.03f,
            -0.062f, 1.378f, -0.016f, 0, -0.05f,
            -0.062f, -0.122f, 1.483f, 0, -0.02f,
            0, 0, 0, 1, 0
    };

}

package com.cme.mm.testdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.cme.mm.testdemo.R;
import com.cme.mm.testdemo.constant.ImgColorMatrixConstant;
import com.cme.mm.testdemo.constant.ImgPositionMatrixConstant;
import com.cme.mm.testdemo.utils.DisplayUtil;
import com.cme.mm.testdemo.widgets.full_custom.ImagePositionMatrixView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Descriptions：图像特效处理Activity——位置
 * <p>
 * Author：ChenME
 * Date：2016/9/29
 * Email：ibelieve1210@163.com
 */
@EActivity(R.layout.activity_change_image_position_matrix)
public class ChangeImagePositionMatrixActivity extends AppCompatActivity {

    @Bean(DisplayUtil.class)
    DisplayUtil dUtils;

    @ViewById(R.id.ipm_InuYasha)
    ImagePositionMatrixView ipm_InuYasha;
    @ViewById(R.id.gl_positionMatrix)
    GridLayout gl_positionMatrix;

    private Bitmap mBitmap;

    private EditText[] mEtArr;

    private float[] mPositionMatrixArr;

    @AfterViews
    void onPageStart() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.inuyasha);

        mEtArr = new EditText[9];
        mPositionMatrixArr = new float[9];

        gl_positionMatrix.post(new Runnable() {
            @Override
            public void run() {
                addEts();
                initMatrix();
            }
        });
    }


    private void setImageMatrix() {
        getMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mPositionMatrixArr);

//        matrix.setRotate(45);
//        matrix.postTranslate(200, 200);

//        matrix.setTranslate(200, 200);
//        matrix.preRotate(45);

//        matrix.setScale(1, -1);
//        matrix.postRotate(45);
//        matrix.postTranslate(0, 200);


        ipm_InuYasha.setImageAndMatrix(mBitmap, matrix);
        ipm_InuYasha.invalidate();
    }

    private void getMatrix() {
        for (int i = 0; i < 9; i++) {
            mPositionMatrixArr[i] = Float.valueOf(mEtArr[i].getText().toString());
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                mEtArr[i].setText(1 + "");
            } else {
                mEtArr[i].setText(0 + "");
            }
        }
    }

    private void setMatrix(float[] matrixArr) {
        for (int i = 0; i < 9; i++) {
            mEtArr[i].setText(matrixArr[i] + "");
        }
    }

    private void addEts() {
        int mEtWidth = gl_positionMatrix.getWidth() / 3;
        int mEtHeight = gl_positionMatrix.getHeight() / 3;
        for (int i = 0; i < 9; i++) {
            EditText editText = new EditText(this);
            editText.setTextSize(dUtils.sp2px(8));
//            editText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
            mEtArr[i] = editText;
            gl_positionMatrix.addView(editText, mEtWidth, mEtHeight);
        }
    }


    @Click(R.id.btn_ok)
    void toConfirm() {
        setImageMatrix();
    }

    @Click(R.id.btn_reset)
    void toReset() {
        initMatrix();
        toConfirm();
    }

    @Click(R.id.btn_translate)
    void toGrayLevelImage() {
        setMatrix(ImgPositionMatrixConstant.POSITION_MATRIX_TRANS);
        toConfirm();
    }

    @Click(R.id.btn_scale)
    void toReverseImage() {
        setMatrix(ImgPositionMatrixConstant.POSITION_MATRIX_SCALE);
        toConfirm();
    }

    @Click(R.id.btn_skew)
    void toReminiscence() {
        setMatrix(ImgPositionMatrixConstant.POSITION_MATRIX_SKEW);
        toConfirm();
    }

    @Click(R.id.btn_rotate)
    void toDiscoloration() {
        setMatrix(ImgPositionMatrixConstant.POSITION_MATRIX_ROTATE);
        toConfirm();
    }
}

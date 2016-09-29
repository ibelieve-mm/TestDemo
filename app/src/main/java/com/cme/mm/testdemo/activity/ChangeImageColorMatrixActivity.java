package com.cme.mm.testdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.cme.mm.testdemo.R;
import com.cme.mm.testdemo.constant.ImgColorMatrixConstant;
import com.cme.mm.testdemo.utils.DisplayUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Descriptions：图像特效处理Activity
 * <p>
 * Author：ChenME
 * Date：2016/9/29
 * Email：ibelieve1210@163.com
 */
@EActivity(R.layout.activity_change_image_color_matrix)
public class ChangeImageColorMatrixActivity extends AppCompatActivity {

    @Bean(DisplayUtil.class)
    DisplayUtil dUtils;

    @ViewById(R.id.iv_InuYasha)
    ImageView iv_InuYasha;
    @ViewById(R.id.gl_colorMatrix)
    GridLayout gv_colorMatrix;

    private Bitmap mBitmap;

    private EditText[] mEtArr;

    private float[] mColorMatrixArr;

    @AfterViews
    void onPageStart() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.inuyasha);
        iv_InuYasha.setImageBitmap(mBitmap);

        mEtArr = new EditText[20];
        mColorMatrixArr = new float[20];

        gv_colorMatrix.post(new Runnable() {
            @Override
            public void run() {
                addEts();
                initMatrix();
            }
        });
    }

    private void setImageMatrix() {
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrixArr);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        iv_InuYasha.setImageBitmap(bitmap);
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrixArr[i] = Float.valueOf(mEtArr[i].getText().toString());
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEtArr[i].setText(1 + "");
            } else {
                mEtArr[i].setText(0 + "");
            }
        }
    }

    private void setMatrix(float[] matrixArr) {
        for (int i = 0; i < 20; i++) {
            mEtArr[i].setText(matrixArr[i] + "");
        }
    }

    private void addEts() {
        int mEtWidth = gv_colorMatrix.getWidth() / 5;
        int mEtHeight = gv_colorMatrix.getHeight() / 4;
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            editText.setTextSize(dUtils.sp2px(6));
//            editText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
            mEtArr[i] = editText;
            gv_colorMatrix.addView(editText, mEtWidth, mEtHeight);
        }
    }


    @Click(R.id.btn_ok)
    void toConfirm() {
        getMatrix();
        setImageMatrix();
    }

    @Click(R.id.btn_reset)
    void toReset() {
        initMatrix();
        toConfirm();
    }

    @Click(R.id.btn_grayLevelImage)
    void toGrayLevelImage() {
        setMatrix(ImgColorMatrixConstant.COLOR_MATRIX_GRAY_LEVEL_IMAGE);
        toConfirm();
    }

    @Click(R.id.btn_reverseImage)
    void toReverseImage() {
        setMatrix(ImgColorMatrixConstant.COLOR_MATRIX_REVERSE_IMAGE);
        toConfirm();
    }

    @Click(R.id.btn_reminiscence)
    void toReminiscence() {
        setMatrix(ImgColorMatrixConstant.COLOR_MATRIX_REMINISCENCE);
        toConfirm();
    }

    @Click(R.id.btn_discoloration)
    void toDiscoloration() {
        setMatrix(ImgColorMatrixConstant.COLOR_MATRIX_DISCOLORATION);
        toConfirm();
    }

    @Click(R.id.btn_highSaturation)
    void toHighSaturation() {
        setMatrix(ImgColorMatrixConstant.COLOR_MATRIX_HIGH_SATURATION);
        toConfirm();
    }
}

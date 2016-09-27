package com.cme.mm.testdemo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Descriptions：
 * <p/>
 * Author：ChenME
 * Date：2016/9/14
 * Email：ibelieve1210@163.com
 */
public class App extends Application {

    public static Context mContext;
    private static Toast toast = null;
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (null == toast) {
                toast = Toast.makeText(mContext, msg.obj.toString(), Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg.obj.toString());
            }
            toast.show();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     * 弹出系统的Toast
     *
     * @param stringMsg
     */
    public static void toast(String stringMsg) {
        Message msg = new Message();
        msg.obj = stringMsg;
        mHandler.sendMessage(msg);
    }

    public static void toast(int stringRes) {
        Message msg = new Message();
        msg.obj = mContext.getString(stringRes);
        mHandler.sendMessage(msg);
    }

    /**
     * 打印Log
     *
     * @param msg
     */
    public static void log(String msg) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Log.i("cme", sdf.format(new Date()) + " ~~~ " + msg);
    }
}

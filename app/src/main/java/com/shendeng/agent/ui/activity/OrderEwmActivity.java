package com.shendeng.agent.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.config.AppCode;
import com.shendeng.agent.util.Y;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

public class OrderEwmActivity extends BaseActivity implements QRCodeView.Delegate {


    @BindView(R.id.zxingview)
    ZBarView mQRCodeView;
    @BindView(R.id.capture_flash)
    ImageView captureFlash;
    @BindView(R.id.ll)
    LinearLayout ll;

    boolean flag = true;
    private String myCode;
    private ProgressDialog waitdialog;

    @Override
    public int getContentViewResId() {
        return R.layout.act_ewm;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("扫描快递单号");
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OrderEwmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mQRCodeView.startSpot();
        mQRCodeView.setDelegate(this);
    }

    @OnClick(R.id.capture_flash)
    public void onViewClicked() {
        light();
    }

    private void light() {
        if (flag) {
            flag = false;
            // 开闪光灯
            mQRCodeView.openFlashlight();
            captureFlash.setTag(null);
            captureFlash.setBackgroundResource(R.drawable.flash_open);
        } else {
            flag = true;
            // 关闪光灯
            mQRCodeView.closeFlashlight();
            captureFlash.setTag("1");
            captureFlash.setBackgroundResource(R.drawable.flash_default);
        }
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Y.e("扫描结果" + result);
        myCode = result;

//        waitdialog = ProgressDialog.show(mContext, null, "已扫描，正在处理···", true, true);
//        waitdialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            public void onDismiss(DialogInterface dialog) {
//
//            }
//        });
        waitdialog.dismiss();

        vibrate();
        if (result.length() == 24) {
            requestData(result);
        } else {
            Y.tLong("您的单号码不正确");
            mQRCodeView.startSpot();
        }
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    public void requestData(String ccid) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        mQRCodeView.startCamera();
    }
}

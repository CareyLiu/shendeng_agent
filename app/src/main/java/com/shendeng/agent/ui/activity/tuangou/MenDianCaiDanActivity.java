package com.shendeng.agent.ui.activity.tuangou;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.util.UIHelper;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class MenDianCaiDanActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_shanchuantupian1)
    ImageView ivShanchuantupian1;
    @BindView(R.id.iv_shanchuantupian2)
    ImageView ivShanchuantupian2;
    @BindView(R.id.iv_shanchuantupian3)
    ImageView ivShanchuantupian3;
    @BindView(R.id.ll_first)
    LinearLayout llFirst;
    @BindView(R.id.iv_shanchuantupian4)
    ImageView ivShanchuantupian4;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    String chooseItem;//0 第一个 1 第二个 2 第三个 3 第四个

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
        ivShanchuantupian1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseItem = "0";
                String[] items = {"拍照", "相册"};
                final ActionSheetDialog dialog = new ActionSheetDialog(MenDianCaiDanActivity.this, items, null);
                dialog.isTitleShow(false).show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        Uri imageUri = Uri.fromFile(file);
                        switch (position) {
                            case 0:
                                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                                break;
                            case 1:
                                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                                break;
                        }
                        dialog.dismiss();

                    }
                });
            }
        });

        ivShanchuantupian2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseItem = "1";
                String[] items = {"拍照", "相册"};
                final ActionSheetDialog dialog = new ActionSheetDialog(MenDianCaiDanActivity.this, items, null);
                dialog.isTitleShow(false).show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        Uri imageUri = Uri.fromFile(file);
                        switch (position) {
                            case 0:
                                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                                break;
                            case 1:
                                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                                break;
                        }
                        dialog.dismiss();

                    }
                });
            }
        });
        ivShanchuantupian3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseItem = "2";
                String[] items = {"拍照", "相册"};
                final ActionSheetDialog dialog = new ActionSheetDialog(MenDianCaiDanActivity.this, items, null);
                dialog.isTitleShow(false).show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        Uri imageUri = Uri.fromFile(file);
                        switch (position) {
                            case 0:
                                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                                break;
                            case 1:
                                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                                break;
                        }
                        dialog.dismiss();

                    }
                });
            }
        });
        ivShanchuantupian4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseItem = "3";
                String[] items = {"拍照", "相册"};
                final ActionSheetDialog dialog = new ActionSheetDialog(MenDianCaiDanActivity.this, items, null);
                dialog.isTitleShow(false).show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        Uri imageUri = Uri.fromFile(file);
                        switch (position) {
                            case 0:
                                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                                break;
                            case 1:
                                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                                break;
                        }
                        dialog.dismiss();

                    }
                });
            }
        });


    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_men_dian_cai_dan;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context leixing 0新建进入 1 二次进入
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MenDianCaiDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("门店图片");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        //此处使用原图路径，不压缩
        File file = new File(result.getImage().getOriginalPath());
//        OkGo.<AppResponse<Upload.DataBean>>post(Urls.SERVER_URL + "msg/upload")
//                .tag(this)//
//                .isSpliceUrl(true)
//                .params("key", Urls.KEY)
//                .params("token", UserManager.getManager(MenDianCaiDanActivity.this).getAppToken())
//                .params("type", "1")
//                .params("file", file)
//                .execute(new JsonCallback<AppResponse<Upload.DataBean>>() {
//                    @Override
//                    public void onSuccess(final Response<AppResponse<Upload.DataBean>> response) {
//                        Glide.with(MenDianCaiDanActivity.this).load(response.body().data.get(0).getFile_all_url()).into(ivShanchuantupian);
//                    }
//
//                    @Override
//                    public void onError(Response<AppResponse<Upload.DataBean>> response) {
//
//                        UIHelper.ToastMessage(MenDianCaiDanActivity.this, response.getException().getMessage());
//                    }
//                });

        switch (chooseItem) {
            case "0":
                Glide.with(MenDianCaiDanActivity.this).load(file).into(ivShanchuantupian1);
                break;
            case "1":
                Glide.with(MenDianCaiDanActivity.this).load(file).into(ivShanchuantupian2);
                break;
            case "2":
                Glide.with(MenDianCaiDanActivity.this).load(file).into(ivShanchuantupian3);
                break;
            case "3":
                Glide.with(MenDianCaiDanActivity.this).load(file).into(ivShanchuantupian4);
                break;

        }



    }

    @Override
    public void takeFail(TResult result, String msg) {
        UIHelper.ToastMessage(mContext, msg);
    }

    @Override
    public void takeCancel() {

        UIHelper.ToastMessage(mContext, "取消选择");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 配置takerPhoto参数
     */
    public CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(800).setAspectY(800);
        return builder.create();
    }

    @SuppressLint("SimpleDateFormat")
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format;
        format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(date);
        return strDate;
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }
}

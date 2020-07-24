package com.shendeng.agent.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifBitmapProvider;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shendeng.agent.R;
import com.shendeng.agent.app.BaseActivity;
import com.shendeng.agent.callback.JsonCallback;
import com.shendeng.agent.config.AppCode;
import com.shendeng.agent.config.AppResponse;
import com.shendeng.agent.config.UserManager;
import com.shendeng.agent.dialog.BottomDialog;
import com.shendeng.agent.dialog.BottomDialogView;
import com.shendeng.agent.model.ShangpinDetailsModel;
import com.shendeng.agent.model.Upload;
import com.shendeng.agent.ui.activity.headimage.ClipImageActivity;
import com.shendeng.agent.util.Urls;
import com.shendeng.agent.util.Y;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class ShangpinZiAddActivity extends BaseActivity {

    @BindView(R.id.ed_xinghao)
    EditText ed_xinghao;
    @BindView(R.id.ed_yuan_money)
    EditText ed_yuan_money;
    @BindView(R.id.ed_new_money)
    EditText ed_new_money;
    @BindView(R.id.ed_kucun)
    EditText ed_kucun;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.ed_hongbao)
    EditText ed_hongbao;
    @BindView(R.id.tv_jia)
    TextView tv_jia;
    @BindView(R.id.tv_delete)
    TextView tv_delete;
    @BindView(R.id.bt_save)
    TextView bt_save;
    @BindView(R.id.iv_delete)
    ImageView iv_delete;
    @BindView(R.id.iv_swich)
    ImageView iv_swich;
    @BindView(R.id.ll_hongbao)
    LinearLayout ll_hongbao;

    private boolean isHaveImg;
    private String shop_product_id;
    private String product_title;
    private String money_old;
    private String money_now;
    private String shop_product_count;
    private String red_set_type;
    private String red_packet;
    private String wares_id;
    private File file;
    private ShangpinDetailsModel.DataBean.PackageListBean packageListBean;

    @Override
    public int getContentViewResId() {
        return R.layout.act_shangpin_zi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String wares_id) {
        Intent intent = new Intent();
        intent.setClass(context, ShangpinZiAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("wares_id", wares_id);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String wares_id, ShangpinDetailsModel.DataBean.PackageListBean packageListBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        wares_id = getIntent().getStringExtra("wares_id");
        packageListBean = (ShangpinDetailsModel.DataBean.PackageListBean) getIntent().getSerializableExtra("packageListBean");
        if (packageListBean != null) {
            shop_product_id = packageListBean.getShop_product_id();
            String index_photo_url = packageListBean.getIndex_photo_url();
            if (TextUtils.isEmpty(index_photo_url)) {
                isHaveImg = false;
            } else {
                Glide.with(mContext).load(index_photo_url).into(iv_add);
                iv_delete.setVisibility(View.VISIBLE);
                isHaveImg = true;
            }

            ed_xinghao.setText(packageListBean.getProduct_title());
            ed_yuan_money.setText(packageListBean.getMoney_old());
            ed_new_money.setText(packageListBean.getMoney_now());
            ed_kucun.setText(packageListBean.getShop_product_count());

            red_set_type = packageListBean.getRed_set_type();
            if (red_set_type.equals("1")) {
                iv_swich.setImageResource(R.mipmap.swich_on);
                ll_hongbao.setVisibility(View.VISIBLE);
                ed_hongbao.setText(packageListBean.getRed_packet());
            } else {
                iv_swich.setImageResource(R.mipmap.swich_off);
                ll_hongbao.setVisibility(View.GONE);
            }
            tv_title.setText("编辑商品型号");
        } else {
            isHaveImg = false;
            red_set_type = "2";
            tv_title.setText("添加商品型号");
        }
    }

    @OnClick({R.id.iv_delete, R.id.iv_add, R.id.tv_jia, R.id.tv_delete, R.id.bt_save, R.id.iv_swich})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                clickAdd();
                break;
            case R.id.tv_jia:
                break;
            case R.id.tv_delete:
                break;
            case R.id.bt_save:
                save();
                break;
            case R.id.iv_delete:
                ImgDelete();
                break;
            case R.id.iv_swich:
                swich();
                break;
        }
    }

    private void swich() {
        if (red_set_type.equals("1")) {
            red_set_type = "2";
            iv_swich.setImageResource(R.mipmap.swich_off);
            ll_hongbao.setVisibility(View.GONE);
        } else {
            red_set_type = "1";
            iv_swich.setImageResource(R.mipmap.swich_on);
            ll_hongbao.setVisibility(View.VISIBLE);
        }
    }

    private void save() {
        product_title = ed_xinghao.getText().toString();
        money_old = ed_yuan_money.getText().toString();
        money_now = ed_new_money.getText().toString();
        shop_product_count = ed_kucun.getText().toString();

        if (TextUtils.isEmpty(product_title)) {
            Y.t("请输入商品型号");
            return;
        }

        if (TextUtils.isEmpty(money_old)) {
            Y.t("请输入商品原价");
            return;
        }

        if (TextUtils.isEmpty(money_now)) {
            Y.t("请输入商品现价");
            return;
        }

        if (TextUtils.isEmpty(shop_product_count)) {
            Y.t("请输入商品库存");
            return;
        }

        if (red_set_type.equals("1")) {
            red_packet = ed_hongbao.getText().toString();
            if (TextUtils.isEmpty(red_packet)) {
                Y.t("请输入返利红包的金额");
                return;
            }
        } else {
            red_packet = "";
        }

        HttpParams params = new HttpParams();
        params.put("code", Urls.code_04194);
        params.put("key", Urls.KEY);
        params.put("token", UserManager.getManager(mContext).getAppToken());
        params.put("product_title", product_title);
        params.put("money_old", money_old);
        params.put("money_now", money_now);
        params.put("shop_product_count", shop_product_count);
        params.put("red_set_type", red_set_type);//是否选择红包 1.是 2.否
        params.put("red_packet", red_packet);
        params.put("wares_id", wares_id);

        if (!TextUtils.isEmpty(shop_product_id)) {
            params.put("shop_product_id", shop_product_id);
        }

        if (file != null) {
            params.put("file", file);
            params.put("type", "1");
        }

        OkGo.<AppResponse<Upload.DataBean>>post(Urls.UPLOAD)
                .tag(this)//
                .isSpliceUrl(true)
                .params(params)
                .execute(new JsonCallback<AppResponse<Upload.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Upload.DataBean>> response) {
                        Y.t("保存成功");
                        setResult(200);
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<Upload.DataBean>> response) {

                    }

                    @Override
                    public void onStart(Request<AppResponse<Upload.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void ImgDelete() {
        iv_add.setImageResource(R.mipmap.shoppicture_icon_add);
        iv_delete.setVisibility(View.GONE);
        file = null;
    }

    private void clickAdd() {
        if (isHaveImg) {

        } else {
            List<String> names = new ArrayList<>();
            names.add("拍照");
            names.add("相册");
            final BottomDialog bottomDialog = new BottomDialog(this);
            bottomDialog.setModles(names);
            bottomDialog.setClickListener(new BottomDialogView.ClickListener() {
                @Override
                public void onClickItem(int pos) {
                    bottomDialog.dismiss();
                    if (pos == 0) {
                        selectPaizhao();
                    } else {
                        selectXiangce();
                    }
                }

                @Override
                public void onClickCancel(View v) {
                    bottomDialog.dismiss();
                }
            });
            bottomDialog.showBottom();
        }
    }

    private void selectPaizhao() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) { // 在android 6.0之前会默认返回true
                    gotoCamera();
                } else {
                    Y.tLong("该应用需要赋予访问相机的权限，不开启将无法正常工作！");
                }
            }
        });
    }

    //调用照相机返回图片临时文件
    private File tempFile;

    private void gotoCamera() {
        //跳转到调用系统相机
        tempFile = createCameraTempFile();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, AppCode.CAMERA_PERMISSIONS_REQUEST_CODE);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     */
    public File createCameraTempFile() {
        return new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/sleepApp/image/"),
                System.currentTimeMillis() + ".jpg");
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    private void selectXiangce() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) { // 在android 6.0之前会默认返回true
                    gotoPic();
                } else {
                    Y.tLong("该应用需要赋予访问相册的权限，不开启将无法正常工作！");
                }
            }
        });
    }

    private void gotoPic() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), AppCode.STORAGE_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppCode.CAMERA_PERMISSIONS_REQUEST_CODE: //调用系统相机返回
                if (resultCode == this.RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case AppCode.STORAGE_PERMISSIONS_REQUEST_CODE:  //调用系统相册返回
                if (resultCode == this.RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case AppCode.REQUEST_CROP_PHOTO:  //剪切图片返回
                handleCrop(resultCode, data);
                if (tempFile != null) {
                    tempFile.delete();
                }
                break;
        }
    }

    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, AppCode.REQUEST_CROP_PHOTO);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == this.RESULT_OK) {
            Uri uri = result.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
            uploadImage(cropImagePath);
        } else if (resultCode == 404) {
            Y.t("剪裁失败");
        }
    }

    public String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public void uploadImage(final String imageString) {
        file = new File(imageString);
        Glide.with(mContext).load(file).into(iv_add);
        iv_delete.setVisibility(View.VISIBLE);
    }
}

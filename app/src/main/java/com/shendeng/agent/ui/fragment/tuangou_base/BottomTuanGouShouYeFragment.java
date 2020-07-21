package com.shendeng.agent.ui.fragment.tuangou_base;


import android.Manifest;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.shendeng.agent.R;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.ui.activity.OrderSaoyisaoActivity;
import com.shendeng.agent.util.UIHelper;
import com.shendeng.agent.util.Y;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class BottomTuanGouShouYeFragment extends BaseFragment {

    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.view_title_line)
    View viewTitleLine;
    @BindView(R.id.iv_shumayanzheng)
    ImageView ivShumayanzheng;
    @BindView(R.id.cl_shumayanzheng)
    ConstraintLayout clShumayanzheng;
    @BindView(R.id.iv_saomayanzheng)
    ImageView ivSaomayanzheng;
    @BindView(R.id.cl_saomayanzheng)
    ConstraintLayout clSaomayanzheng;
    @BindView(R.id.iv_maidanshoukuan)
    ImageView ivMaidanshoukuan;
    @BindView(R.id.cl_maidanshoukuan)
    ConstraintLayout clMaidanshoukuan;
    @BindView(R.id.iv_dingdanguanli)
    ImageView ivDingdanguanli;
    @BindView(R.id.cl_dingdanguanli)
    ConstraintLayout clDingdanguanli;
    @BindView(R.id.ll_dingbu)
    LinearLayout llDingbu;
    @BindView(R.id.tv_jinqitian)
    TextView tvJinqitian;
    @BindView(R.id.view_jinqitian_line)
    View viewJinqitianLine;
    @BindView(R.id.tv_zuotian)
    TextView tvZuotian;
    @BindView(R.id.view_zuotian_line)
    View viewZuotianLine;
    @BindView(R.id.tv_jintian)
    TextView tvJintian;
    @BindView(R.id.view_jintian_line)
    View viewJintianLine;
    @BindView(R.id.cl_mendianshuju)
    ConstraintLayout clMendianshuju;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_yingshoujine)
    TextView tvYingshoujine;
    @BindView(R.id.cl_mendian)
    ConstraintLayout clMendian;
    @BindView(R.id.tv_tuangou)
    TextView tvTuangou;
    @BindView(R.id.tv_dingdanshu)
    TextView tvDingdanshu;
    @BindView(R.id.iv_icon_right)
    ImageView ivIconRight;
    @BindView(R.id.tv_yingshoujine_huashu)
    TextView tvYingshoujineHuashu;
    @BindView(R.id.cl_yingshoujine)
    ConstraintLayout clYingshoujine;
    @BindView(R.id.tv_shishoujine)
    TextView tvShishoujine;
    @BindView(R.id.cl_tuangou)
    ConstraintLayout clTuangou;
    @BindView(R.id.tv_maidan)
    TextView tvMaidan;
    @BindView(R.id.tv_maidan_dingdanshu)
    TextView tvMaidanDingdanshu;
    @BindView(R.id.iv_icon_right_1)
    ImageView ivIconRight1;
    @BindView(R.id.tv_maidan_yingshoujine_huashu)
    TextView tvMaidanYingshoujineHuashu;
    @BindView(R.id.cl_maidan_yingshoujine)
    ConstraintLayout clMaidanYingshoujine;
    @BindView(R.id.tv_maidan_shishoujine)
    TextView tvMaidanShishoujine;
    boolean jintian;//今天
    boolean zuotian;//昨天
    boolean jinqitian;//近七天
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));
    }


    public static BottomTuanGouShouYeFragment newInstance() {
        Bundle args = new Bundle();
        BottomTuanGouShouYeFragment fragment = new BottomTuanGouShouYeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_tuangou_shouye;
    }


    @Override
    protected void initView(View view) {

    }


    @Override
    protected void initLogic() {


        tvJinqitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewJinqitianLine.setVisibility(View.VISIBLE);
                viewJintianLine.setVisibility(View.GONE);
                viewZuotianLine.setVisibility(View.GONE);
            }
        });

        tvJintian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewJinqitianLine.setVisibility(View.GONE);
                viewJintianLine.setVisibility(View.VISIBLE);
                viewZuotianLine.setVisibility(View.GONE);
            }
        });

        tvZuotian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewJinqitianLine.setVisibility(View.GONE);
                viewJintianLine.setVisibility(View.GONE);
                viewZuotianLine.setVisibility(View.VISIBLE);
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void ewm() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) { // 在android 6.0之前会默认返回true
                    OrderSaoyisaoActivity.actionStartForResult(getActivity(), 100);
                } else {
                    Y.tLong("该应用需要赋予访问相机的权限，不开启将无法正常工作！");
                }
            }
        });
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .titleBar(toolbar).fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .statusBarColor(R.color.white)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

}

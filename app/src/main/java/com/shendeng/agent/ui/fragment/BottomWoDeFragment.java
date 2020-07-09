package com.shendeng.agent.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.shendeng.agent.R;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;
import com.shendeng.agent.ui.activity.WodeQainbaoActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BottomWoDeFragment extends BaseFragment {
    public static final String TAG = "BottomWoDeFragment";
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_set)
    ImageView iv_set;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_admin)
    TextView tv_admin;
    @BindView(R.id.iv_shenfen)
    ImageView iv_shenfen;
    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;
    @BindView(R.id.tv_shoucang)
    TextView tv_shoucang;
    @BindView(R.id.tv_kaquan)
    TextView tv_kaquan;
    @BindView(R.id.ll_qianbao)
    LinearLayout ll_qianbao;
    @BindView(R.id.ll_about)
    LinearLayout ll_about;
    @BindView(R.id.ll_dizhi)
    LinearLayout ll_dizhi;
    @BindView(R.id.ll_yuangong)
    LinearLayout ll_yuangong;
    @BindView(R.id.ll_qiehuan)
    LinearLayout ll_qiehuan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));

    }

    public static BottomWoDeFragment newInstance() {
        Bundle args = new Bundle();
        BottomWoDeFragment fragment = new BottomWoDeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_mian_mine;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initLogic() {


    }


    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .titleBar(toolbar)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
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


    @OnClick({R.id.ll_qianbao, R.id.ll_about, R.id.ll_dizhi, R.id.ll_yuangong, R.id.ll_qiehuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_qianbao:
                openActivity(WodeQainbaoActivity.class);
                break;
            case R.id.ll_about:
                break;
            case R.id.ll_dizhi:
                break;
            case R.id.ll_yuangong:
                break;
            case R.id.ll_qiehuan:
                break;
        }
    }
}

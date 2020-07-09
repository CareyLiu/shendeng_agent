package com.shendeng.agent.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.shendeng.agent.R;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;

import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BottomDingDanFragment extends BaseFragment {
    public static final String TAG = "BottomDingDanFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));

    }

    public static BottomDingDanFragment newInstance() {
        Bundle args = new Bundle();
        BottomDingDanFragment fragment = new BottomDingDanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_mian_order;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void initToolBar(View rootView) {
        super.initToolBar(rootView);
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


}

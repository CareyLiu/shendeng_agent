package com.shendeng.agent.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.jakewharton.rxbinding.view.RxView;
import com.shendeng.agent.R;
import com.shendeng.agent.basicmvp.BaseFragment;
import com.shendeng.agent.bean.Notice;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BottomXiaoXiFragment extends BaseFragment {
    public static final String TAG = "BottomXiaoXiFragment";
    @BindView(R.id.top_view)
    View topView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));
    }

    public static BottomXiaoXiFragment newInstance() {
        Bundle args = new Bundle();
        BottomXiaoXiFragment fragment = new BottomXiaoXiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_mian_msg;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initLogic() {

    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolBar(View rootView) {
        super.initToolBar(rootView);
        iv_rightTitle.setVisibility(View.VISIBLE);
        tv_title.setText("消息");
        tv_title.setTextSize(17);
        tv_title.setTextColor(this.getResources().getColor(R.color.color_494949));
        tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        toolbar.setNavigationIcon(R.mipmap.backbutton);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void immersionInit() {
        mImmersionBar
                .statusBarView(toolbar)
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImmersionBar = ImmersionBar.with(this);
        immersionInit();
    }
    private boolean islogic = false;
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        immersionInit();
        Log.d("--3--", "333333");
        if (islogic) {
            initLogic();
        }
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

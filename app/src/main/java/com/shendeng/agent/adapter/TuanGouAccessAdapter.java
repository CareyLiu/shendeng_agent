package com.shendeng.agent.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.model.tuangou.TuanPinglunModel;

import java.util.List;

public class TuanGouAccessAdapter extends BaseQuickAdapter<TuanPinglunModel.DataBean.AssessListBean, BaseViewHolder> {


    public TuanGouAccessAdapter(int layoutResId, @Nullable List<TuanPinglunModel.DataBean.AssessListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanPinglunModel.DataBean.AssessListBean item) {

    }
}

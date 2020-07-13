package com.shendeng.agent.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.MingxiDetailsModel;
import com.shendeng.agent.model.MingxiModel;
import com.shendeng.agent.util.Y;

import java.util.List;

import androidx.annotation.Nullable;

public class MingxiDetailsAdapter extends BaseQuickAdapter<MingxiDetailsModel.DataBean.DistributionListBean, BaseViewHolder> {
    public MingxiDetailsAdapter(int layoutResId, @Nullable List<MingxiDetailsModel.DataBean.DistributionListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MingxiDetailsModel.DataBean.DistributionListBean item) {
        helper.setText(R.id.tv_money, item.getMoney());
        helper.setText(R.id.tv_name, item.getName());
    }
}

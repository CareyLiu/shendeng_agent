package com.shendeng.agent.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.JiesuanModel;

import java.util.List;

public class TaoCanGuanLiHomeAdapter extends BaseQuickAdapter<JiesuanModel.DataBean, BaseViewHolder> {
    public TaoCanGuanLiHomeAdapter(int layoutResId, @Nullable List<JiesuanModel.DataBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, JiesuanModel.DataBean item) {
//        Glide.with(mContext).load(item.getIndex_photo_url()).into((ImageView) helper.getView(R.id.iv_head));
//        helper.setText(R.id.tv_name, item.getUser_name());
//        helper.setText(R.id.tv_data, item.getPay_time());
//        helper.setText(R.id.tv_money, item.getPay_money());

        helper.addOnClickListener(R.id.constrain);
    }
}

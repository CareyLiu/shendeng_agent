package com.shendeng.agent.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.JiesuanModel;
import com.shendeng.agent.model.TaoCanListModel;

import java.util.List;

public class TaoCanGuanLiHomeAdapter extends BaseQuickAdapter<TaoCanListModel.DataBean.TaocanListBean, BaseViewHolder> {
    public TaoCanGuanLiHomeAdapter(int layoutResId, @Nullable List<TaoCanListModel.DataBean.TaocanListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, TaoCanListModel.DataBean.TaocanListBean item) {
        helper.addOnClickListener(R.id.constrain);
        helper.setText(R.id.tv_product_name, item.getWares_name());
        helper.setText(R.id.tv_riqi, "这里填套餐简介");
        helper.setText(R.id.tv_yuanjia, "￥" + item.getShop_money_old());
        TextView tvYuanJia = helper.getView(R.id.tv_yuanjia);
        tvYuanJia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        helper.setText(R.id.tv_xianjia, item.getShop_money_now());
        Glide.with(mContext).load(item.getWares_photo_url()).error(R.mipmap.shangpin_yixiajia_pic).placeholder(R.mipmap.shangpin_yixiajia_pic).into((ImageView) helper.getView(R.id.iv_product));
    }
}

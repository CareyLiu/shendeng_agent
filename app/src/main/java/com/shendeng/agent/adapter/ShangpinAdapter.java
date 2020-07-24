package com.shendeng.agent.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.ShangpinModel;
import com.shendeng.agent.util.TimeUtils;

import java.util.List;

import androidx.annotation.Nullable;

public class ShangpinAdapter extends BaseQuickAdapter<ShangpinModel.DataBean, BaseViewHolder> {

    public ShangpinAdapter(int layoutResId, @Nullable List<ShangpinModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShangpinModel.DataBean item) {
        Glide.with(mContext).load(item.getWares_photo_url()).into((ImageView) helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_title_name, item.getShop_product_title());
        String money = item.getMoney();
        if (TextUtils.isEmpty(money)) {
            helper.setText(R.id.tv_red, "暂未添加商品型号");
        } else {
            helper.setText(R.id.tv_red, "¥" + money);
        }
        helper.setText(R.id.tv_xiaoliao, "销量：" + item.getWares_sales_volume());
        helper.setText(R.id.tv_time, item.getCreate_time());
    }
}

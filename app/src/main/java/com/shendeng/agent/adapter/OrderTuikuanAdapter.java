package com.shendeng.agent.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.OrderModel;
import com.shendeng.agent.model.OrderTuikuanModel;

import java.util.List;

import androidx.annotation.Nullable;

public class OrderTuikuanAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public OrderTuikuanAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text, item);
    }
}

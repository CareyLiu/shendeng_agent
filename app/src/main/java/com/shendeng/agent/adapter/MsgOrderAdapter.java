package com.shendeng.agent.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.MsgModel;

import java.util.List;

import androidx.annotation.Nullable;

public class MsgOrderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MsgOrderAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
    }
}

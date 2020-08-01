package com.shendeng.agent.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.MingxiDetailsModel;
import com.shendeng.agent.model.MsgModel;

import java.util.List;

import androidx.annotation.Nullable;

public class MsgAdapter extends BaseQuickAdapter<MsgModel, BaseViewHolder> {
    public MsgAdapter(int layoutResId, @Nullable List<MsgModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgModel item) {
        helper.setText(R.id.tv_name, item.getName());


    }
}

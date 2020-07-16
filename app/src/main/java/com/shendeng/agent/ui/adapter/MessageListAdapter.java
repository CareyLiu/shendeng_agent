package com.shendeng.agent.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.MessageModel;


import java.util.List;

public class MessageListAdapter extends BaseQuickAdapter<MessageModel.DataBean, BaseViewHolder> {
    public MessageListAdapter(int layoutResId, @Nullable List<MessageModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageModel.DataBean item) {
        ImageView ivImage = helper.getView(R.id.iv_image);
        Glide.with(mContext)
                .load(item.getOther_img_url())
                .into((ImageView) ivImage);
        helper.setText(R.id.tv_type, item.getNotify_text());
        helper.setText(R.id.tv_text, item.getCreate_time());
        helper.addOnClickListener(R.id.constrain);
        helper.addOnLongClickListener(R.id.constrain);

    }
}

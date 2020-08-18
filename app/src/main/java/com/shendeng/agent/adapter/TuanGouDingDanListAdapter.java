package com.shendeng.agent.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shendeng.agent.R;
import com.shendeng.agent.model.MessageModel;
import com.shendeng.agent.model.TuanGouOrderListModel;

import java.util.List;

public class TuanGouDingDanListAdapter extends BaseQuickAdapter<TuanGouOrderListModel.DataBean, BaseViewHolder> {
    public TuanGouDingDanListAdapter(int layoutResId, @Nullable List<TuanGouOrderListModel.DataBean> data) {
        super(layoutResId, data);
    }

    /**
     * msg_code	返回码
     * msg	应答描述
     * data	应答数据
     * form_id	支付订单id
     * shop_pay_check	卖家订单状态
     * shop_product_title	套餐名称
     * shop_pay_check_name	卖家订单状态名称
     * wares_type	商品类型：1.商城商品 2.拼购商品 3.团购套餐
     * product	shop_form_id	订单id
     * index_photo_url	商品封面图
     * create_time	下单时间
     * total_money_all	套餐总价
     *
     * @param helper
     * @param item
     */

    @Override
    protected void convert(BaseViewHolder helper, TuanGouOrderListModel.DataBean item) {
        //helper.setText(R.id.tv_taocan_ming,item.)

        helper.setText(R.id.tv_taocan_ming, item.getShop_product_title());
        helper.setText(R.id.tv_xiadanshijian, item.getCreate_time());
        Glide.with(mContext).load(item.getIndex_photo_url()).into((ImageView) helper.getView(R.id.iv_product));
        helper.setText(R.id.tv_xiadanshijian, item.getCreate_time());
        helper.setText(R.id.tv_shuliang, item.getPay_count());
        helper.setText(R.id.tv_zongjia, item.getTotal_money_all());
//状态：0.全部1.待付款 5.到店消费 6.待评价 7.已评价 8.退款申请 9.退款中 10.已关闭
        switch (item.getShop_pay_check()) {
            case "0":
                helper.setText(R.id.tv_zhuangtai, "全部");
                break;
            case "1":
                helper.setText(R.id.tv_zhuangtai, "待付款");
                break;
            case "5":
                helper.setText(R.id.tv_zhuangtai, "到店消费");
                break;
            case "6":
                helper.setText(R.id.tv_zhuangtai, "待评价");
                break;
            case "7":
                helper.setText(R.id.tv_zhuangtai, "已评价");
                break;
            case "8":
                helper.setText(R.id.tv_zhuangtai, "退款申请");
                break;
            case "9":
                helper.setText(R.id.tv_zhuangtai, "退款中");
                break;
            case "10":
                helper.setText(R.id.tv_zhuangtai, "已关闭");
                break;

        }

//        ImageView ivImage = helper.getView(R.id.iv_image);
//        Glide.with(mContext)
//                .load(item.getOther_img_url())
//                .into((ImageView) ivImage);
//        helper.setText(R.id.tv_type, item.getNotify_text());
//        helper.setText(R.id.tv_text, item.getCreate_time());
        helper.addOnClickListener(R.id.constrain);
//        helper.addOnLongClickListener(R.id.constrain);

    }
}

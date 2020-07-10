package com.shendeng.agent.model;

import java.util.List;

public class OrderModel {

    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"product":[{"shop_form_id":"1818","shop_product_title":"佐森 山影","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9839","product_title":"套餐1","form_product_money":"180.00","pay_count":"1","shop_form_text":""}],"user_accid":"","user_name":"未设置","form_id":"2018040320313602769","user_img_url":"https://shop.hljsdkj.com/manage/subsystem/main/toUserUrl?of_user_id=628","shop_pay_check":"3","shop_pay_check_name":"待发货","express_url":"","wares_type":"1","receiver_text":"收货人：zhangs","receiver_name":"zhangs","user_addr_all":"黑龙江省哈尔滨市道里区xxxhh","receiver_phone":"185456987"}]
     */

    private String next;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * product : [{"shop_form_id":"1818","shop_product_title":"佐森 山影","index_photo_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9839","product_title":"套餐1","form_product_money":"180.00","pay_count":"1","shop_form_text":""}]
         * user_accid :
         * user_name : 未设置
         * form_id : 2018040320313602769
         * user_img_url : https://shop.hljsdkj.com/manage/subsystem/main/toUserUrl?of_user_id=628
         * shop_pay_check : 3
         * shop_pay_check_name : 待发货
         * express_url :
         * wares_type : 1
         * receiver_text : 收货人：zhangs
         * receiver_name : zhangs
         * user_addr_all : 黑龙江省哈尔滨市道里区xxxhh
         * receiver_phone : 185456987
         */

        private String user_accid;
        private String user_name;
        private String form_id;
        private String user_img_url;
        private String shop_pay_check;
        private String shop_pay_check_name;
        private String express_url;
        private String wares_type;
        private String receiver_text;
        private String receiver_name;
        private String user_addr_all;
        private String receiver_phone;
        private List<ProductBean> product;

        public String getUser_accid() {
            return user_accid;
        }

        public void setUser_accid(String user_accid) {
            this.user_accid = user_accid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getForm_id() {
            return form_id;
        }

        public void setForm_id(String form_id) {
            this.form_id = form_id;
        }

        public String getUser_img_url() {
            return user_img_url;
        }

        public void setUser_img_url(String user_img_url) {
            this.user_img_url = user_img_url;
        }

        public String getShop_pay_check() {
            return shop_pay_check;
        }

        public void setShop_pay_check(String shop_pay_check) {
            this.shop_pay_check = shop_pay_check;
        }

        public String getShop_pay_check_name() {
            return shop_pay_check_name;
        }

        public void setShop_pay_check_name(String shop_pay_check_name) {
            this.shop_pay_check_name = shop_pay_check_name;
        }

        public String getExpress_url() {
            return express_url;
        }

        public void setExpress_url(String express_url) {
            this.express_url = express_url;
        }

        public String getWares_type() {
            return wares_type;
        }

        public void setWares_type(String wares_type) {
            this.wares_type = wares_type;
        }

        public String getReceiver_text() {
            return receiver_text;
        }

        public void setReceiver_text(String receiver_text) {
            this.receiver_text = receiver_text;
        }

        public String getReceiver_name() {
            return receiver_name;
        }

        public void setReceiver_name(String receiver_name) {
            this.receiver_name = receiver_name;
        }

        public String getUser_addr_all() {
            return user_addr_all;
        }

        public void setUser_addr_all(String user_addr_all) {
            this.user_addr_all = user_addr_all;
        }

        public String getReceiver_phone() {
            return receiver_phone;
        }

        public void setReceiver_phone(String receiver_phone) {
            this.receiver_phone = receiver_phone;
        }

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * shop_form_id : 1818
             * shop_product_title : 佐森 山影
             * index_photo_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9839
             * product_title : 套餐1
             * form_product_money : 180.00
             * pay_count : 1
             * shop_form_text :
             */

            private String shop_form_id;
            private String shop_product_title;
            private String index_photo_url;
            private String product_title;
            private String form_product_money;
            private String pay_count;
            private String shop_form_text;

            public String getShop_form_id() {
                return shop_form_id;
            }

            public void setShop_form_id(String shop_form_id) {
                this.shop_form_id = shop_form_id;
            }

            public String getShop_product_title() {
                return shop_product_title;
            }

            public void setShop_product_title(String shop_product_title) {
                this.shop_product_title = shop_product_title;
            }

            public String getIndex_photo_url() {
                return index_photo_url;
            }

            public void setIndex_photo_url(String index_photo_url) {
                this.index_photo_url = index_photo_url;
            }

            public String getProduct_title() {
                return product_title;
            }

            public void setProduct_title(String product_title) {
                this.product_title = product_title;
            }

            public String getForm_product_money() {
                return form_product_money;
            }

            public void setForm_product_money(String form_product_money) {
                this.form_product_money = form_product_money;
            }

            public String getPay_count() {
                return pay_count;
            }

            public void setPay_count(String pay_count) {
                this.pay_count = pay_count;
            }

            public String getShop_form_text() {
                return shop_form_text;
            }

            public void setShop_form_text(String shop_form_text) {
                this.shop_form_text = shop_form_text;
            }
        }
    }
}

package com.shendeng.agent.model;

import java.util.List;

public class TaoCanDetailsModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 0
     * data : [{"wares_photo_id":"","shop_product_title":"重庆小面","wares_photo_url":"","img_list":[],"shop_money_now":"100.00","item_name":"","shop_money_old":"100.00","taocan_list":[{"menu_detail_id":"2","menu_pay":"1","menu_text":"去","menu_count":"1"}],"wares_name":"重庆小面","rules_list":[{"prompt_detail_id":"13","prompt_text":"1.规则1"},{"prompt_detail_id":"14","prompt_text":"规则2"},{"prompt_detail_id":"15","prompt_text":"规则3"},{"prompt_detail_id":"16","prompt_text":"4. kaishi le"}],"wares_state":"2"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

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
         * wares_photo_id :
         * shop_product_title : 重庆小面
         * wares_photo_url :
         * img_list : []
         * shop_money_now : 100.00
         * item_name :
         * shop_money_old : 100.00
         * taocan_list : [{"menu_detail_id":"2","menu_pay":"1","menu_text":"去","menu_count":"1"}]
         * wares_name : 重庆小面
         * rules_list : [{"prompt_detail_id":"13","prompt_text":"1.规则1"},{"prompt_detail_id":"14","prompt_text":"规则2"},{"prompt_detail_id":"15","prompt_text":"规则3"},{"prompt_detail_id":"16","prompt_text":"4. kaishi le"}]
         * wares_state : 2
         */

        private String wares_photo_id;
        private String shop_product_title;
        private String wares_photo_url;
        private String shop_money_now;
        private String item_name;
        private String shop_money_old;
        private String wares_name;
        private String wares_state;
        private List<?> img_list;
        private List<TaocanListBean> taocan_list;
        private List<RulesListBean> rules_list;

        public String getWares_photo_id() {
            return wares_photo_id;
        }

        public void setWares_photo_id(String wares_photo_id) {
            this.wares_photo_id = wares_photo_id;
        }

        public String getShop_product_title() {
            return shop_product_title;
        }

        public void setShop_product_title(String shop_product_title) {
            this.shop_product_title = shop_product_title;
        }

        public String getWares_photo_url() {
            return wares_photo_url;
        }

        public void setWares_photo_url(String wares_photo_url) {
            this.wares_photo_url = wares_photo_url;
        }

        public String getShop_money_now() {
            return shop_money_now;
        }

        public void setShop_money_now(String shop_money_now) {
            this.shop_money_now = shop_money_now;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getShop_money_old() {
            return shop_money_old;
        }

        public void setShop_money_old(String shop_money_old) {
            this.shop_money_old = shop_money_old;
        }

        public String getWares_name() {
            return wares_name;
        }

        public void setWares_name(String wares_name) {
            this.wares_name = wares_name;
        }

        public String getWares_state() {
            return wares_state;
        }

        public void setWares_state(String wares_state) {
            this.wares_state = wares_state;
        }

        public List<?> getImg_list() {
            return img_list;
        }

        public void setImg_list(List<?> img_list) {
            this.img_list = img_list;
        }

        public List<TaocanListBean> getTaocan_list() {
            return taocan_list;
        }

        public void setTaocan_list(List<TaocanListBean> taocan_list) {
            this.taocan_list = taocan_list;
        }

        public List<RulesListBean> getRules_list() {
            return rules_list;
        }

        public void setRules_list(List<RulesListBean> rules_list) {
            this.rules_list = rules_list;
        }

        public static class TaocanListBean {
            /**
             * menu_detail_id : 2
             * menu_pay : 1
             * menu_text : 去
             * menu_count : 1
             */

            private String menu_detail_id;
            private String menu_pay;
            private String menu_text;
            private String menu_count;

            public String getMenu_detail_id() {
                return menu_detail_id;
            }

            public void setMenu_detail_id(String menu_detail_id) {
                this.menu_detail_id = menu_detail_id;
            }

            public String getMenu_pay() {
                return menu_pay;
            }

            public void setMenu_pay(String menu_pay) {
                this.menu_pay = menu_pay;
            }

            public String getMenu_text() {
                return menu_text;
            }

            public void setMenu_text(String menu_text) {
                this.menu_text = menu_text;
            }

            public String getMenu_count() {
                return menu_count;
            }

            public void setMenu_count(String menu_count) {
                this.menu_count = menu_count;
            }
        }

        public static class RulesListBean {
            /**
             * prompt_detail_id : 13
             * prompt_text : 1.规则1
             */

            private String prompt_detail_id;
            private String prompt_text;

            public String getPrompt_detail_id() {
                return prompt_detail_id;
            }

            public void setPrompt_detail_id(String prompt_detail_id) {
                this.prompt_detail_id = prompt_detail_id;
            }

            public String getPrompt_text() {
                return prompt_text;
            }

            public void setPrompt_text(String prompt_text) {
                this.prompt_text = prompt_text;
            }
        }
    }
}

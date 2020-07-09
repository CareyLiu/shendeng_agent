/**
 *
 */
package com.shendeng.agent.model;

import java.util.List;

/**
 * 登陆用户信息
 * @author yantong
 * 下午3:14:17
 */
public class LoginUser {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 0
     * data : [{"of_user_id":"499","app_token":"1594265m33639200K000i000I000J0","user_name":"月亮","token_rong":"+02RA7sOtVAL4tWDQL2ASj92dU/6JxiSKYNlCv3mCCfTqmMTNJFbNg==@cd7u.cn.rongnav.com;cd7u.cn.rongcfg.com","accid":"jcz_sub_191","enter_type":"1","user_img":"https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png"}]
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
         * of_user_id : 499
         * app_token : 1594265m33639200K000i000I000J0
         * user_name : 月亮
         * token_rong : +02RA7sOtVAL4tWDQL2ASj92dU/6JxiSKYNlCv3mCCfTqmMTNJFbNg==@cd7u.cn.rongnav.com;cd7u.cn.rongcfg.com
         * accid : jcz_sub_191
         * enter_type : 1
         * user_img : https://jy.hljsdkj.com/commons/easyui/images/portrait86x86.png
         */

        private String of_user_id;
        private String app_token;
        private String user_name;
        private String token_rong;
        private String accid;
        private String enter_type;
        private String user_img;

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getApp_token() {
            return app_token;
        }

        public void setApp_token(String app_token) {
            this.app_token = app_token;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getToken_rong() {
            return token_rong;
        }

        public void setToken_rong(String token_rong) {
            this.token_rong = token_rong;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getEnter_type() {
            return enter_type;
        }

        public void setEnter_type(String enter_type) {
            this.enter_type = enter_type;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }
    }
}
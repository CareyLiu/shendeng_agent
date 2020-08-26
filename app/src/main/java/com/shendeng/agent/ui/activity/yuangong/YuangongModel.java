package com.shendeng.agent.ui.activity.yuangong;

import java.util.List;

public class YuangongModel {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"of_user_id":"667","app_token":"1598426c07119300i000r000l000g0","sub_photo_id":"","user_name":"未设置","branch_id_one":"378","app_token_time":"","wx_token":"","branch_id_two":"-1","sub_user_wx_id":"","branch_id":"378","role_id":"364","subsystem_id":"jcz","branch_name":"系统管理","accid":"jcz_sub_213","user_phone":"18249030234","sub_user_id":"213","inst_id":"258","state":"正常","sub_state":"1","create_time":"2020-03-26","sub_photo_url":"","role_name":"系统管理员","token_rong":"","sub_user_no":"0005","wx_id":"","wx_gzpt_id":"","wx_unionid":"","tp_power":"1"}]
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
         * of_user_id : 667
         * app_token : 1598426c07119300i000r000l000g0
         * sub_photo_id :
         * user_name : 未设置
         * branch_id_one : 378
         * app_token_time :
         * wx_token :
         * branch_id_two : -1
         * sub_user_wx_id :
         * branch_id : 378
         * role_id : 364
         * subsystem_id : jcz
         * branch_name : 系统管理
         * accid : jcz_sub_213
         * user_phone : 18249030234
         * sub_user_id : 213
         * inst_id : 258
         * state : 正常
         * sub_state : 1
         * create_time : 2020-03-26
         * sub_photo_url :
         * role_name : 系统管理员
         * token_rong :
         * sub_user_no : 0005
         * wx_id :
         * wx_gzpt_id :
         * wx_unionid :
         * tp_power : 1
         */

        private String of_user_id;
        private String app_token;
        private String sub_photo_id;
        private String user_name;
        private String branch_id_one;
        private String app_token_time;
        private String wx_token;
        private String branch_id_two;
        private String sub_user_wx_id;
        private String branch_id;
        private String role_id;
        private String subsystem_id;
        private String branch_name;
        private String accid;
        private String user_phone;
        private String sub_user_id;
        private String inst_id;
        private String state;
        private String sub_state;
        private String create_time;
        private String sub_photo_url;
        private String role_name;
        private String token_rong;
        private String sub_user_no;
        private String wx_id;
        private String wx_gzpt_id;
        private String wx_unionid;
        private String tp_power;

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

        public String getSub_photo_id() {
            return sub_photo_id;
        }

        public void setSub_photo_id(String sub_photo_id) {
            this.sub_photo_id = sub_photo_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getBranch_id_one() {
            return branch_id_one;
        }

        public void setBranch_id_one(String branch_id_one) {
            this.branch_id_one = branch_id_one;
        }

        public String getApp_token_time() {
            return app_token_time;
        }

        public void setApp_token_time(String app_token_time) {
            this.app_token_time = app_token_time;
        }

        public String getWx_token() {
            return wx_token;
        }

        public void setWx_token(String wx_token) {
            this.wx_token = wx_token;
        }

        public String getBranch_id_two() {
            return branch_id_two;
        }

        public void setBranch_id_two(String branch_id_two) {
            this.branch_id_two = branch_id_two;
        }

        public String getSub_user_wx_id() {
            return sub_user_wx_id;
        }

        public void setSub_user_wx_id(String sub_user_wx_id) {
            this.sub_user_wx_id = sub_user_wx_id;
        }

        public String getBranch_id() {
            return branch_id;
        }

        public void setBranch_id(String branch_id) {
            this.branch_id = branch_id;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getSubsystem_id() {
            return subsystem_id;
        }

        public void setSubsystem_id(String subsystem_id) {
            this.subsystem_id = subsystem_id;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getSub_user_id() {
            return sub_user_id;
        }

        public void setSub_user_id(String sub_user_id) {
            this.sub_user_id = sub_user_id;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSub_state() {
            return sub_state;
        }

        public void setSub_state(String sub_state) {
            this.sub_state = sub_state;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getSub_photo_url() {
            return sub_photo_url;
        }

        public void setSub_photo_url(String sub_photo_url) {
            this.sub_photo_url = sub_photo_url;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        public String getToken_rong() {
            return token_rong;
        }

        public void setToken_rong(String token_rong) {
            this.token_rong = token_rong;
        }

        public String getSub_user_no() {
            return sub_user_no;
        }

        public void setSub_user_no(String sub_user_no) {
            this.sub_user_no = sub_user_no;
        }

        public String getWx_id() {
            return wx_id;
        }

        public void setWx_id(String wx_id) {
            this.wx_id = wx_id;
        }

        public String getWx_gzpt_id() {
            return wx_gzpt_id;
        }

        public void setWx_gzpt_id(String wx_gzpt_id) {
            this.wx_gzpt_id = wx_gzpt_id;
        }

        public String getWx_unionid() {
            return wx_unionid;
        }

        public void setWx_unionid(String wx_unionid) {
            this.wx_unionid = wx_unionid;
        }

        public String getTp_power() {
            return tp_power;
        }

        public void setTp_power(String tp_power) {
            this.tp_power = tp_power;
        }
    }
}

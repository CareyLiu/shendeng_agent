/**
 *
 */
package com.shendeng.agent.config;

import android.content.Context;

import com.shendeng.agent.app.PreferenceHelper;
import com.shendeng.agent.model.LoginUser;


/**
 * 登陆用户信息管理类
 */
public class UserManager {

    private static UserManager mUserManager;
    private static Context mContext;

    private UserManager(Context ctx) {
        mContext = ctx;
    }

    public static UserManager getManager(Context ctx) {
        if (mUserManager == null) {
            mUserManager = new UserManager(ctx);
        }
        return mUserManager;
    }


    public String getUserId() {
        return PreferenceHelper.getInstance(mContext).getString("of_user_id", "");
    }

    public String getAppToken() {
        return PreferenceHelper.getInstance(mContext).getString("app_token", "");
    }

    public String getUserName() {
        return PreferenceHelper.getInstance(mContext).getString("user_name", "");
    }

    public String getRongYun() {
        return PreferenceHelper.getInstance(mContext).getString("token_rong", "");
    }

    public String getAccid() {
        return PreferenceHelper.getInstance(mContext).getString("accid", "");
    }

    public String getEnter_type() {
        return PreferenceHelper.getInstance(mContext).getString("enter_type", "");
    }

    public String getUserImg() {
        return PreferenceHelper.getInstance(mContext).getString("user_img", "");
    }

    //保存用户信息
    public void saveUser(LoginUser.DataBean user) {
        if (user != null) {
            PreferenceHelper.getInstance(mContext).putString("of_user_id", user.getOf_user_id());
            PreferenceHelper.getInstance(mContext).putString("app_token", user.getApp_token());
            PreferenceHelper.getInstance(mContext).putString("user_name", user.getUser_name());
            PreferenceHelper.getInstance(mContext).putString("token_rong", user.getToken_rong());
            PreferenceHelper.getInstance(mContext).putString("accid", user.getAccid());
            PreferenceHelper.getInstance(mContext).putString("enter_type", user.getEnter_type());
            PreferenceHelper.getInstance(mContext).putString("user_img", user.getUser_img());
        }
    }

    /**
     * 删除用户信息
     */
    public void removeUser() {
        PreferenceHelper.getInstance(mContext).removeKey("of_user_id");
        PreferenceHelper.getInstance(mContext).removeKey("app_token");
        PreferenceHelper.getInstance(mContext).removeKey("user_name");
        PreferenceHelper.getInstance(mContext).removeKey("token_rong");
        PreferenceHelper.getInstance(mContext).removeKey("accid");
        PreferenceHelper.getInstance(mContext).removeKey("enter_type");
        PreferenceHelper.getInstance(mContext).removeKey("user_img");
    }

}
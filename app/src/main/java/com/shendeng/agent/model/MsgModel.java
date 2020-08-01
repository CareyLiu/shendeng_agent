package com.shendeng.agent.model;

import android.content.Intent;

public class MsgModel {
    private int ImgId;
    private String name;

    public MsgModel(int imgId, String name) {
        ImgId = imgId;
        this.name = name;
    }

    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

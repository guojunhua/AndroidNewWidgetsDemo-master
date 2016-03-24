package com.ht.htsdk_library.bean;

import java.io.Serializable;

/**
 * Created by 郭君华 on 2016/1/18.
 * Email：guojunhua3369@163.com
 */
public class UserInfo implements Serializable {

    public static final String ID = "_id";
    public static final String USERID = "uid";
    public static final String NAME = "name";
    public static final String TOKEN = "token";
    public static final String DIVICE = "divice";
    public static final String EMAIL = "email";
    public static final String FACEBOOKINFO = "facebookinfo";

    private String uid; // 用户id
    private String name;
    private String token;
    //0===false,1===true
    private int divice;
    private int email;
    private int facebookinfo;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getID() {
        return ID;
    }

    public static String getUSERID() {
        return USERID;
    }

    public int getDivice() {
        return divice;
    }

    public void setDivice(int divice) {
        this.divice = divice;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getFacebookinfo() {
        return facebookinfo;
    }

    public void setFacebookinfo(int facebookinfo) {
        this.facebookinfo = facebookinfo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", divice=" + divice +
                ", email=" + email +
                ", facebookinfo=" + facebookinfo +
                '}';
    }
}

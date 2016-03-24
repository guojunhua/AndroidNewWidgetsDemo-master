package com.ht.htsdk_library;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 郭君华 on 2016/1/18.
 * Email：guojunhua3369@163.com
 */
public class MyApp extends Application {
    public final static String url = "http://service.gamehetu.com/passport/%s?app=%s&data=%s";
    public final static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1jDlsT3SgLrjYVuxegFJlygU8" + "\r"
            + "hN0CqxHZI2c6Tn9/VmlmSugAtkm9u/VIm7/EjUhreQ6sGr5MyZXF68cH38Say7Oa" + "\r"
            + "b7ws2oHbaE2LfetwAXBC/THVH1l59HacLJiOnHFvBGKjPEQULyx4N5Gj9qAYbWeY" + "\r"
            + "sWFrt5f4g0bd4c+mKQIDAQAB" + "\r";
    public String name;
    public String token;

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
}

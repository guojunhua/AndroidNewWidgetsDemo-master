package com.ht.yiyuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ht.yiyuan.bean.A;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.show.api.ShowApiRequest;

import java.io.UnsupportedEncodingException;
import java.util.Date;
public class MainActivity extends AppCompatActivity {
    private String appid = "15933";
    private String secret = "3d820a902d4b43f5bf81bb22e84ba744";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
//final TextView txt = (TextView) this.findViewById(R.id.textview);
//Button myBtn = (Button) this.findViewById(R.id.button);
//final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {
//
//    public A a;
//
//    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable e) {
//        //做一些异常处理
//        e.printStackTrace();
//    }
//
//    public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
//        try {
//            System.out.println("response is :" + new String(responseBody, "utf-8"));
//            //txt.setText(new String(responseBody, "utf-8") + new Date());
//            String json = new String(responseBody, "utf-8");
//            a = new Gson().fromJson(json, A.class);
//            txt.setText(a.toString());
//            //在此对返回内容做处理
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//    }
//};
//myBtn.setOnClickListener(new View.OnClickListener() {
//public void onClick(View v) {
//        new ShowApiRequest("http://route.showapi.com/64-20", appid, secret)
//        .setResponseHandler(resHandler)
//        .post();
//
//        }
//        });
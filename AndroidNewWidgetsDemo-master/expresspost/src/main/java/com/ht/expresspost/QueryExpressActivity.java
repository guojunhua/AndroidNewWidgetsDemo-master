package com.ht.expresspost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.show.api.ShowApiRequest;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

/**
 * Created by 郭君华 on 2016/3/11.
 * Email：guojunhua3369@163.com
 */
public class QueryExpressActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText et_simpleName;
    public EditText et_order;
    public Button query;
    public ListView listview;
    public ExpressQuery expressQuery;
    private String appid = "15933";
    private String secret = "3d820a902d4b43f5bf81bb22e84ba744";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        et_simpleName = ((EditText) findViewById(R.id.query_simpleName));
        et_order = ((EditText) findViewById(R.id.query_order));
        query = ((Button) findViewById(R.id.query));
        listview = ((ListView) findViewById(R.id.listview));
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String simpleName = et_simpleName.getText().toString();
        String order = et_order.getText().toString();
        httpPost(simpleName,order);
    }
    public void httpPost(String simpleName,String order) {
        final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {

            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable e) {
                //做一些异常处理
                e.printStackTrace();
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody, "utf-8");
                    expressQuery = new Gson().fromJson(json, ExpressQuery.class);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        };
        new ShowApiRequest("http://route.showapi.com/64-19", appid, secret)
                .setResponseHandler(resHandler)
                .addTextPara("com",simpleName)
                .addTextPara("nu",order)
                .post();
    }
}

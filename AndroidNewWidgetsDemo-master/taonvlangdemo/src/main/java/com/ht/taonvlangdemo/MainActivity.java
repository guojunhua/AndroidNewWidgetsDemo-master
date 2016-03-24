package com.ht.taonvlangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.show.api.ShowApiRequest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MyRecyclerViewAdapter.OnItemClickListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerViewAdapter mRecyclerViewAdapter;
    private String appid = "15933";
    private String secret = "3d820a902d4b43f5bf81bb22e84ba744";
    private List<String> mData;
    private static final int SPAN_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = ((SwipeRefreshLayout) findViewById(R.id.id_swiperefreshlayout));
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        // 刷新时，指示器旋转后变化的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);
        mLayoutManager = new GridLayoutManager(this, SPAN_COUNT, GridLayoutManager.HORIZONTAL, false);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        mData = new ArrayList<String>();
        final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable e) {
                //做一些异常处理
                e.printStackTrace();
            }
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody, "utf-8");
                    TypeBean type = new TypeBean();
                    type = new Gson().fromJson(json, TypeBean.class);
                    Log.e("---guo---",type.toString());
                    mData = type.getShowapi_res_body().getAllTypeList();
                    mRecyclerViewAdapter.setData(mData);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        };

        new ShowApiRequest("http://route.showapi.com/126-1", appid, secret)
                .setResponseHandler(resHandler)
                .post();
        mRecyclerViewAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onRefresh() {
// 刷新时模拟数据的变化
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable e) {
                        //做一些异常处理
                        e.printStackTrace();
                    }
                    public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                        try {
                            String json = new String(responseBody, "utf-8");
                            TypeBean type = new TypeBean();
                            type = new Gson().fromJson(json, TypeBean.class);
                            mData = type.getShowapi_res_body().getAllTypeList();
                            mRecyclerViewAdapter.setData(mData);
                            mRecyclerViewAdapter.notifyDataSetChanged();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new ShowApiRequest("http://route.showapi.com/126-2", appid, secret)
                        .setResponseHandler(resHandler)
                        .post();
            }
        }, 1000);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("type",mData.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}

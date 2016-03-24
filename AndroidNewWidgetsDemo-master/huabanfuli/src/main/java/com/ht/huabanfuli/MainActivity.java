package com.ht.huabanfuli;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private String appid = "15933";
    private String secret = "3d820a902d4b43f5bf81bb22e84ba744";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int SPAN_COUNT = 2;
    private MyStaggeredViewAdapter mStaggeredAdapter;
    private List<HuTuBean> mData;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = ((SwipeRefreshLayout) findViewById(R.id.id_swiperefreshlayout));
        mRecyclerView = ((RecyclerView) findViewById(R.id.id_recyclerview));
        mLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mData = new ArrayList<HuTuBean>();
        mStaggeredAdapter = new MyStaggeredViewAdapter(MainActivity.this);
        // 刷新时，指示器旋转后变化的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable e) {
                //做一些异常处理
                e.printStackTrace();
            }

            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody, "utf-8");
                    Log.e("---jun---", json);
                    mData = parserJson(json);
                    mStaggeredAdapter.setData(mData);
                    mStaggeredAdapter.notifyDataSetChanged();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        };

        new ShowApiRequest("http://route.showapi.com/819-1", appid, secret)
                .setResponseHandler(resHandler)
                .addTextPara("type", "37")
                .addTextPara("num", "20")
                .addTextPara("page", "1")
                .post();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mStaggeredAdapter);
        //mStaggeredAdapter.setOnItemClickListener(this);

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
                            Log.e("---jun---", json);
                            mData = parserJson(json);
                            mStaggeredAdapter.setData(mData);
                            mStaggeredAdapter.notifyDataSetChanged();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new ShowApiRequest("http://route.showapi.com/819-1", appid, secret)
                        .setResponseHandler(resHandler)
                        .addTextPara("type", "38")
                        .addTextPara("num", "20")
                        .addTextPara("page", ""+ i)
                        .post();
            }
        }, 1000);
        i++;
    }
    public List<HuTuBean> parserJson(String json) {
        List<HuTuBean> data = null;
        try {
            data = new ArrayList<HuTuBean>();
            JSONObject object = new JSONObject(json).getJSONObject("showapi_res_body");
            for (int i = 0; i < 9; i++) {
                JSONObject o = object.getJSONObject("" + i);
                HuTuBean huTuBean = new HuTuBean();
                huTuBean.setThumb(o.getString("thumb"));
                huTuBean.setTitle(o.getString("title"));
                huTuBean.setUrl(o.getString("url"));
                data.add(huTuBean);
            }
            Log.e("---haha---", data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //this.mData.addAll(data);
        return data;
    }
}

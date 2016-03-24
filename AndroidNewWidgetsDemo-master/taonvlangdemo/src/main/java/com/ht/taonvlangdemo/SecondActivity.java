package com.ht.taonvlangdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.show.api.ShowApiRequest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭君华 on 2016/3/4.
 * Email：guojunhua3369@163.com
 */
public class SecondActivity extends AppCompatActivity {
    private String appid = "15933";
    private String secret = "3d820a902d4b43f5bf81bb22e84ba744";
    private ListView listView;

    private List<BeautifityBean> date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_second);
//        mImg = (ImageView) findViewById(R.id.id_content);
//        mRecyclerView = (MyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mAdapter = new GalleryAdapter(this, mDatas);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener()
//        {
//            @Override
//            public void onChange(View view, int position)
//            {
//                mImg.setImageResource(mDatas.get(position));
//            };
//        });
//
//        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener()
//        {
//            @Override
//            public void onItemClick(View view, int position)
//            {
////				Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT)
////						.show();
//                mImg.setImageResource(mDatas.get(position));
//            }
//        });

//     }
        //date = new ArrayList<BeautifityBean>();
        final AsyncHttpResponseHandler resHandler = new AsyncHttpResponseHandler() {
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable e) {
                //做一些异常处理
                e.printStackTrace();
            }

            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody, "utf-8");
                    BeautifityBean type = new Gson().fromJson(json, BeautifityBean.class);
                    Log.e("---type---",type.toString());
                    date.add(type);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        };

//        new ShowApiRequest("http://route.showapi.com/126-2", appid, secret)
//                .setResponseHandler(resHandler)
//                .addTextPara("type", intent.getStringExtra("type"))
//                .addTextPara("order", "")
//                .addTextPara("page", "1")
//                .post();
    }
}

package com.ht.expresspost;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by 郭君华 on 2016/3/11.
 * Email：guojunhua3369@163.com
 */
public class WebActivity extends AppCompatActivity {
    private String url;
    private WebView webview;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        LinearLayout rootViewLayout = new LinearLayout(this);
        rootViewLayout.setOrientation(LinearLayout.VERTICAL);
        textView = new TextView(this);
        textView.setTextColor(Color.RED);
        //生成水平进度条
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        webview = new WebView(this);
        rootViewLayout.addView(textView);
        rootViewLayout.addView(progressBar);
        rootViewLayout.addView(webview);
        setContentView(R.layout.activity_web);
        // ~~~ 绑定控件
        webview = (WebView) findViewById(R.id.webview);

        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        //这里吐槽一下这J8网站 写你妹的排队,排你大爷
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                //页面下载完毕,却不代表页面渲染完毕显示出来
                //WebChromeClient中progress==100时也是一样
                if (webview.getContentHeight() != 0) {
                    //这个时候网页才显示
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //自身加载新链接,不做外部跳转
                view.loadUrl(url);
                return true;
            }

        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);
                //这里将textView换成你的progress来设置进度
                if (newProgress == 0) {
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                }
                textView.setText(newProgress + "hahahahaha");
                textView.postInvalidate();
                progressBar.setProgress(newProgress);
                progressBar.postInvalidate();
                if (newProgress == 100) {
                    textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webview.loadUrl(url);
    }
    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}

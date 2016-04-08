package org.mobile.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 郭君华 on 2016/4/7.
 * Email：guojunhua3369@163.com
 */
public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getIntent().getStringExtra("id");
        if (id.equals("a")){
            Intent intent = new Intent(MyActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else if (id.equals("b")){
            Intent intent = new Intent(MyActivity.this,SecondActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

package org.mobile.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 郭君华 on 2016/4/7.
 * Email：guojunhua3369@163.com
 */
public class FirestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirestActivity.this,MyActivity.class);
                intent.putExtra("id","a");
                startActivity(intent);
            }
        });
    }
}

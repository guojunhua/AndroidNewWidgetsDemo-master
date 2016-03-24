package com.ht.htsdk_library;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 郭君华 on 2016/1/20.
 * Email：guojunhua3369@163.com
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    public Button btnLogin;
    public Button btnBind;
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btnLogin = ((Button) findViewById(R.id.login));
        btnBind = ((Button) findViewById(R.id.bind));
        tv = ((TextView) findViewById(R.id.text_game));
        btnLogin.setOnClickListener(this);
        btnBind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                Intent login = new Intent(GameActivity.this,MainActivity.class);
                login.putExtra("stats","login");
                login.putExtra("appId","100000");
                startActivity(login);
                break;
            case R.id.bind:
                Intent bind = new Intent(GameActivity.this,BindActivity.class);
                startActivity(bind);
                finish();
                break;
        }
    }
}

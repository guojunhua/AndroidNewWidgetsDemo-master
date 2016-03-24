package com.ht.htsdk_library;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.ht.htsdk_library.bean.UserInfo;
import com.ht.htsdk_library.utils.Base64Utils;
import com.ht.htsdk_library.utils.DataHelper;
import com.ht.htsdk_library.utils.MacAddress;

/**
 * Created by 郭君华 on 2016/1/19.
 * Email：guojunhua3369@163.com
 */
public class BindActivity extends AppCompatActivity implements View.OnClickListener {
    public CheckBox divice_cb;
    public Button divict_btn;
    public CheckBox account_cb;
    public Button account_btn;
    public CheckBox facebook_cb;
    public Button facebook_btn;

    private String appId;
    private String apiType = "bind";
    public DataHelper dh;
    public UserInfo user;
    public SharedPreferences sp;
    public ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        initView();
        dh = new DataHelper(this);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        appId = sp.getString("appId", "");
        if (sp.getString("name","")!=null){
            user = dh.GetUserInfo(sp.getString("name",""));
            boolean divice = sp.getBoolean("divice",false);
            boolean account = sp.getBoolean("account",false);
            boolean facebook = sp.getBoolean("facebook",false);
            Log.e("divice",""+divice);
            if (user != null && user.getDivice()==1||divice) {
                divice_cb.setChecked(true);
                divice_cb.setText("已绑定");
                divice_cb.setEnabled(false);
                divict_btn.setEnabled(false);
            }
            if (user != null && user.getEmail()==1||account) {
                account_cb.setChecked(true);
                account_cb.setText("已绑定");
                account_btn.setEnabled(false);
                account_cb.setEnabled(false);
            }
            if (user != null && user.getFacebookinfo()==1||facebook) {
                facebook_cb.setChecked(true);
                facebook_cb.setText("已绑定");
                facebook_btn.setEnabled(false);
                facebook_cb.setEnabled(false);
            }
        }else{
            Toast.makeText(BindActivity.this, "尚未登录请登录！！", Toast.LENGTH_SHORT).show();
        }
    }

    public void initView() {
        divice_cb = ((CheckBox) findViewById(R.id.divice_stats));
        divict_btn = ((Button) findViewById(R.id.divice_btn));
        account_cb = ((CheckBox) findViewById(R.id.account_stats));
        account_btn = ((Button) findViewById(R.id.account_btn));
        facebook_cb = ((CheckBox) findViewById(R.id.facebook_stats));
        facebook_btn = ((Button) findViewById(R.id.facebook_btn));
        back = ((ImageView) findViewById(R.id.image_back_bind));
        divict_btn.setOnClickListener(this);
        account_btn.setOnClickListener(this);
        facebook_btn.setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindActivity.this,GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int btnId = v.getId();
        Intent mainBind = new Intent(BindActivity.this,MainActivity.class);
        mainBind.putExtra("appId",appId);
        mainBind.putExtra("stats",apiType);
        if (btnId == R.id.divice_btn) {
            startActivity(mainBind);
            finish();
        } else if (btnId == R.id.account_btn) {
            Intent account = new Intent(BindActivity.this,LoginActivity.class);
            account.putExtra("stats","bind");
            startActivity(account);
            finish();
        } else if (btnId == R.id.facebook_btn) {
            startActivity(mainBind);
            finish();
        }
    }
}

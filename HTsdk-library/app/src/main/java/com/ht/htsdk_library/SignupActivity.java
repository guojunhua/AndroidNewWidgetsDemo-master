package com.ht.htsdk_library;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ht.htsdk_library.bean.LoginBean;
import com.ht.htsdk_library.bean.UserInfo;
import com.ht.htsdk_library.utils.Base64Utils;
import com.ht.htsdk_library.utils.DataHelper;

/**
 * Created by 郭君华 on 2016/1/18.
 * Email：guojunhua3369@163.com
 */
public class SignupActivity extends AppCompatActivity {
    private String apiType = "register";
    private String appId;
    private String data;

    private EditText et_sign_email, et_sign_password;
    private String username;
    private String password;
    private ImageView image_back;
    public Intent intent;

    private TextView tv_title;
    private ImageView iv_anim;
    private AnimationDrawable animation;
    private AlertDialog dialog;
    public DataHelper dh;
    public LoginBean loginBean;
    private RequestQueue mQueue;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    public String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et_sign_email = ((EditText) findViewById(R.id.signup_username));
        et_sign_password = ((EditText) findViewById(R.id.signup_pwd));
        image_back = ((ImageView) findViewById(R.id.image_back));

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registIntent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(registIntent);
                finish();
            }
        });
        dh = new DataHelper(this);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        edit = sp.edit();
        appId = sp.getString("appId","");
    }

    @Override
    protected void onStart() {
        super.onStart();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_layout, null);
        tv_title = ((TextView) view.findViewById(R.id.alert_tv));
        iv_anim = ((ImageView) view.findViewById(R.id.alert_iv));
        iv_anim.setBackgroundResource(R.drawable.loadanimation);
        animation = ((AnimationDrawable) iv_anim.getBackground());
        builder.setView(view);
        dialog = builder.create();
        tv_title.setText(this.getString(R.string.loading));
    }

    public void onClick(View v) {
        try {
            username = et_sign_email.getText().toString();
            password = et_sign_password.getText().toString();
            if (Base64Utils.isEmpty(username, password)) {
                Toast.makeText(SignupActivity.this, R.string.null_email_or_password_tip, Toast.LENGTH_SHORT).show();
            } else if (username.length() < 6 || username.length() > 32) {
                Toast.makeText(SignupActivity.this, R.string.illegal_length_tip, Toast.LENGTH_SHORT).show();
                et_sign_email.setText("");
            } else if (Base64Utils.isContains(username) && !Base64Utils.isEmail(username)) {
                Toast.makeText(SignupActivity.this, R.string.illegal_uname_tip, Toast.LENGTH_SHORT).show();
                et_sign_email.setText("");
            } else if (!Base64Utils.isContains(username) && !Base64Utils.isString(username)) {
                Toast.makeText(SignupActivity.this, R.string.illegal_contain_tip, Toast.LENGTH_SHORT).show();
                et_sign_email.setText("");
            } else if (password.length()<6||password.length() > 32) {
                Toast.makeText(SignupActivity.this, R.string.illegal_pwd_tip, Toast.LENGTH_SHORT).show();
                et_sign_password.setText("");
            } else {
                String userInfo = "username=" + username + "&password=" + password;
                Log.e("--hvd--", " " + userInfo);
                data = Base64Utils.encryptData(userInfo);
                String url = String.format(MyApp.url, apiType, appId, data);
                Log.e("---url--", " " + url);
                getJsonData(url, username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getJsonData(String url, final String name1) {
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String json) {
                        loginBean = new Gson().fromJson(json, LoginBean.class);
                        if (loginBean != null) {
                            dialog.dismiss();
                            if (loginBean.getCode() == 0) {
                                Toast.makeText(SignupActivity.this, R.string.signup_success, Toast.LENGTH_SHORT).show();
                                UserInfo user = new UserInfo();
                                user.setUid(name1);
                                user.setName(loginBean.getData().getName());
                                user.setToken(loginBean.getData().getToken());
                                user.setDivice(0);
                                user.setEmail(0);
                                user.setFacebookinfo(0);
                                dh.SaveUserInfo(user);
                                edit.putString("name", loginBean.getData().getName());
                                edit.apply();
//                                    try {
//                                        String userInfo = "name=" + loginBean.getData().getName() + "&uid=" + loginBean.getData().getUid() + "&token=" + loginBean.getData().getToken();
//                                        UnityPlayer.UnitySendMessage("Main Camera", "sdkCallback", userInfo);
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }
                                Intent account = new Intent(SignupActivity.this, GameActivity.class);
                                startActivity(account);
                                finish();
                            } else if (loginBean.getCode() == 1) {
                                Toast.makeText(SignupActivity.this, R.string.error_operation, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40100) {
                                Toast.makeText(SignupActivity.this, R.string.error_RSA, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40101) {
                                Toast.makeText(SignupActivity.this, R.string.error_parametr, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40102) {
                                Toast.makeText(SignupActivity.this, R.string.error_token, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40103) {
                                Toast.makeText(SignupActivity.this,R.string.over_time, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40104) {
                                Toast.makeText(SignupActivity.this, R.string.signup_fail, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40105) {
                                Toast.makeText(SignupActivity.this, R.string.login_field_tip, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40106) {
                                Toast.makeText(SignupActivity.this, R.string.user_exist_tip, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.i("VolleyUtils", "==" + arg0.getMessage());
            }
        });
        mQueue.add(request);
    }
}

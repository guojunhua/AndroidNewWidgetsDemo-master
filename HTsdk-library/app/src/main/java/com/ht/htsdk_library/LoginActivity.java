package com.ht.htsdk_library;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String apiType;
    private String data;


    private EditText et_email, et_password;
    private Button btn_login, btn_signup;
    private String username;
    private String password;
    public ImageView back;
    public Intent intent;
    public String appId;

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
        intent = getIntent();
        str = intent.getStringExtra("stats");
        if (str.equals("login")) {
            apiType = "login";
        } else if (str.equals("bind")) {
            apiType = "bind";
        }
        setContentView(R.layout.activity_login);
        et_email = ((EditText) findViewById(R.id.username));
        et_password = ((EditText) findViewById(R.id.pwd));
        btn_login = ((Button) findViewById(R.id.gump_login));
        btn_signup = ((Button) findViewById(R.id.gump_signup));
        back = ((ImageView) findViewById(R.id.image_back_login));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(registIntent);
                finish();
            }
        });
        dh = new DataHelper(this);
        btn_login.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
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
    @Override
    public void onClick(View v) {
        username = et_email.getText().toString();
        password = et_password.getText().toString();
        int btnId = v.getId();
        if (btnId == R.id.gump_login) {
            action1();
        } else if (btnId == R.id.gump_signup) {
            action2();
        }
    }

    public void action1() {
        if (Base64Utils.isEmpty(username, password)) {
            Toast.makeText(LoginActivity.this, R.string.null_email_or_password_tip, Toast.LENGTH_SHORT).show();
        } else if (username.length() < 6 || username.length() > 32) {
            Toast.makeText(LoginActivity.this, R.string.illegal_length_tip, Toast.LENGTH_SHORT).show();
            et_email.setText("");
        } else if (Base64Utils.isContains(username) && !Base64Utils.isEmail(username)) {
            Toast.makeText(LoginActivity.this, R.string.illegal_uname_tip, Toast.LENGTH_SHORT).show();
            et_email.setText("");
        } else if (!Base64Utils.isContains(username) && !Base64Utils.isString(username)) {
            Toast.makeText(LoginActivity.this, R.string.illegal_contain_tip, Toast.LENGTH_SHORT).show();
            et_email.setText("");
        } else if (password.length()<6||password.length() > 32) {
            Toast.makeText(LoginActivity.this, R.string.illegal_pwd_tip, Toast.LENGTH_SHORT).show();
            et_password.setText("");
        } else {
            animation.start();
            dialog.show();
           if (apiType.equals("login")){
               String userInfo = "username=" + username + "&password=" + password;
               data = Base64Utils.encryptData(userInfo);
               String url = String.format(MyApp.url, apiType, appId, data);
               Log.e("---gege---", " " + userInfo + "----" + url);
               getJsonData(url, username);
           }else if (apiType.equals("bind")){
               String userInfo = "username=" + username + "&password=" + password;
               data = Base64Utils.encryptData(userInfo);
               String url01 = String.format(MyApp.url, "login", appId, data);
               Log.e("---url01--", " " + url01);
               getBindJson(url01,username);
           }
        }
    }

    public void action2() {
        Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(signup);
        finish();
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
                                Toast.makeText(LoginActivity.this, R.string.login_success_tip, Toast.LENGTH_SHORT).show();
                                UserInfo user = new UserInfo();
                                user.setUid(name1);
                                user.setName(loginBean.getData().getName());
                                user.setToken(loginBean.getData().getToken());
                                if (loginBean.getData().getName().contains("#device")) {
                                    user.setDivice(1);
                                } else {
                                    user.setDivice(0);
                                }
                                if (!loginBean.getData().getName().contains("#device") && !loginBean.getData().getName().contains("#facebook")) {
                                    user.setEmail(1);
                                } else {
                                    user.setEmail(0);
                                }
                                if (loginBean.getData().getName().contains("#facebook")) {
                                    user.setFacebookinfo(1);
                                } else {
                                    user.setFacebookinfo(0);
                                }
                                dh.SaveUserInfo(user);
                                edit.putString("name", loginBean.getData().getName());
                                edit.apply();
//                                    try {
//                                        String userInfo = "name=" + loginBean.getData().getName() + "&uid=" + loginBean.getData().getUid() + "&token=" + loginBean.getData().getToken();
//                                        UnityPlayer.UnitySendMessage("Main Camera", "sdkCallback", userInfo);
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }
                                Intent account = new Intent(LoginActivity.this,GameActivity.class);
                                startActivity(account);
                                finish();
                            }else if (loginBean.getCode() == 1) {
                                Toast.makeText(LoginActivity.this, R.string.error_operation, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40100) {
                                Toast.makeText(LoginActivity.this, R.string.error_RSA, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40101) {
                                Toast.makeText(LoginActivity.this, R.string.error_parametr, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40102) {
                                Toast.makeText(LoginActivity.this, R.string.error_token, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40103) {
                                Toast.makeText(LoginActivity.this,R.string.over_time, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40104) {
                                Toast.makeText(LoginActivity.this, R.string.signup_fail, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40105) {
                                Toast.makeText(LoginActivity.this, R.string.login_field_tip, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40106) {
                                Toast.makeText(LoginActivity.this, R.string.user_exist_tip, Toast.LENGTH_SHORT).show();
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
    public void getBindJson(String url, final String name) {
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String json) {
                        loginBean = new Gson().fromJson(json, LoginBean.class);
                        if (loginBean.getCode() == 0) {
                            String auth = loginBean.getData().getName();
                            String bindInfo = "type=email&auth=" + auth + "&name=" + name + "&token=" + loginBean.getData().getToken();
                            data = Base64Utils.encryptData(bindInfo);
                            String bindurl = String.format(MyApp.url, apiType, appId, data);
                            getBindJson01(bindurl);
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

    public void getBindJson01(String url) {
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String json) {
                        loginBean = new Gson().fromJson(json, LoginBean.class);
                        Log.e("---ooo---", loginBean.getCode() + " ");
                        if (loginBean.getCode() == 0 ) {
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this, R.string.bind_success, Toast.LENGTH_SHORT).show();
                            edit.putBoolean("account", true);
                            edit.apply();
                            Intent divice = new Intent(LoginActivity.this, BindActivity.class);
                            startActivity(divice);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, R.string.bind_field, Toast.LENGTH_SHORT).show();
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

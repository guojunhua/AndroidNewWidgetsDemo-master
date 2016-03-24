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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.ht.htsdk_library.bean.LoginBean;
import com.ht.htsdk_library.bean.UserInfo;
import com.ht.htsdk_library.utils.Base64Utils;
import com.ht.htsdk_library.utils.DataHelper;
import com.ht.htsdk_library.utils.MacAddress;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String apiType;
    private String appId;
    private String stats;
    private String data;

    private String diviceId;
    private String brand;

    private Button btn_tourist, btn_account;

    public ProfileTracker profileTracker;
    private CallbackManager callbackManager;
    public String name;
    public String id;


    private TextView tv_title;
    private ImageView iv_anim;
    private AnimationDrawable animation;
    private AlertDialog dialog;
    public DataHelper dh;
    public LoginBean loginBean;
    private RequestQueue mQueue;

    private Intent intent;
    private String str;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        intent = getIntent();
        appId = intent.getStringExtra("appId");
        str = intent.getStringExtra("stats");
        if (str.equals("login")) {
            apiType = "login";
        } else if (str.equals("bind")) {
            apiType = "bind";
        }
        callbackManager = CallbackManager.Factory.create();
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                updateUI();
            }
        };
        setContentView(R.layout.activity_main);
        initView();
        dh = new DataHelper(this);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        edit = sp.edit();
        edit.putString("appId", appId);
        edit.apply();
    }

    private void initView() {
        btn_tourist = ((Button) findViewById(R.id.tourist));
        btn_account = ((Button) findViewById(R.id.account));
        btn_tourist.setOnClickListener(this);
        btn_account.setOnClickListener(this);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("callback"," "+data.getStringExtra("id"));
    }

    @Override
    public void onClick(View v) {
        int btnId = v.getId();
        if (btnId == R.id.tourist) {
            action1();
        } else if (btnId == R.id.account) {
            action2();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    public void action1() {
        animation.start();
        dialog.show();
        diviceId = new MacAddress(this).getMacAddressAndroid();
        brand = MacAddress.getBrand();
        Toast.makeText(MainActivity.this, "" + diviceId + "--------------" + brand, Toast.LENGTH_SHORT).show();
        if (apiType.equals("login")) {
            String userInfo = "username=" + diviceId + "#device&name=" + brand;
            data = Base64Utils.encryptData(userInfo);
            String url = String.format(MyApp.url, apiType, appId, data);
            getJsonData(url, brand, 0);

        } else if (apiType.equals("bind")) {
            String userInfo = "username=" + diviceId + "#device&name=" + brand;
            data = Base64Utils.encryptData(userInfo);
            String url01 = String.format(MyApp.url, "login", appId, data);
            Log.e("---url01--", " " + url01);
            getBindJson(url01, brand, 0);
        }

    }

    public void action2() {
        Intent accountIntent = new Intent(MainActivity.this, LoginActivity.class);
        accountIntent.putExtra("stats", "login");
        startActivity(accountIntent);
        finish();
    }

    private void updateUI() {
        try {
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                Log.e("---haha--", "-------" + profile.getId() + "-----------" + profile.getName());
                id = profile.getId();
                name = profile.getName();
                if (apiType.equals("login")) {
                    String userInfo = "username=" + id + "#facebook&name=" + name;
                    data = Base64Utils.encryptData(userInfo);
                    String url = String.format(MyApp.url, apiType, appId, data);
                    Log.e("---url--", " " + url);
                    getJsonData(url, name, 1);

                } else if (apiType.equals("bind")) {
                    String userInfo = "username=" + id + "#facebook&name=" + name;
                    data = Base64Utils.encryptData(userInfo);
                    String url01 = String.format(MyApp.url, "login", appId, data);
                    Log.e("---url01--", " " + url01);
                    getBindJson(url01, name, 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getJsonData(String url, final String name1, final int n) {
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String json) {
                        loginBean = new Gson().fromJson(json, LoginBean.class);
                        if (loginBean != null) {
                            dialog.dismiss();
                            if (loginBean.getCode() == 0) {
                                Toast.makeText(MainActivity.this, R.string.login_success_tip, Toast.LENGTH_SHORT).show();
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
                                if (n == 0) {
                                    edit.putBoolean("divice", true);
                                }
                                edit.apply();
                                finish();
//                                    try {
//                                        String userInfo = "name=" + loginBean.getData().getName() + "&uid=" + loginBean.getData().getUid() + "&token=" + loginBean.getData().getToken();
//                                        UnityPlayer.UnitySendMessage("Main Camera", "sdkCallback", userInfo);
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }
                            } else if (loginBean.getCode() == 1) {
                                Toast.makeText(MainActivity.this, R.string.error_operation, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40100) {
                                Toast.makeText(MainActivity.this, R.string.error_RSA, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40101) {
                                Toast.makeText(MainActivity.this, R.string.error_parametr, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40102) {
                                Toast.makeText(MainActivity.this, R.string.error_token, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40103) {
                                Toast.makeText(MainActivity.this,R.string.over_time, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40104) {
                                Toast.makeText(MainActivity.this, R.string.signup_fail, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40105) {
                                Toast.makeText(MainActivity.this, R.string.login_field_tip, Toast.LENGTH_SHORT).show();
                            } else if (loginBean.getCode() == 40106) {
                                Toast.makeText(MainActivity.this, R.string.user_exist_tip, Toast.LENGTH_SHORT).show();
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

    public void getBindJson(String url, final String name, final int n) {
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String json) {
                        loginBean = new Gson().fromJson(json, LoginBean.class);
                        if (loginBean.getCode() == 0 && n == 0) {
                            String auth = loginBean.getData().getName().replace("#device", "");
                            String bindInfo = "type=device&auth=" + auth + "&name=" + name + "&token=" + loginBean.getData().getToken();
                            data = Base64Utils.encryptData(bindInfo);
                            String bindurl = String.format(MyApp.url, apiType, appId, data);
                            getBindJson01(bindurl, 0);
                        }
                        if (loginBean.getCode() == 0 && n == 1) {
                            String auth = loginBean.getData().getName().replace("#facebook", "");
                            String bindInfo = "type=facebook&auth=" + auth + "&name=" + name + "&token=" + loginBean.getData().getToken();
                            data = Base64Utils.encryptData(bindInfo);
                            String bindurl = String.format(MyApp.url, apiType, appId, data);
                            getBindJson01(bindurl, 1);
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

    public void getBindJson01(String url, final int n) {
        mQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String json) {
                        loginBean = new Gson().fromJson(json, LoginBean.class);
                        Log.e("---ooo---", loginBean.getCode() + " ");
                        if (loginBean.getCode() == 0 && n == 0) {
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, R.string.bind_success, Toast.LENGTH_SHORT).show();
                            edit.putBoolean("divice", true);
                            edit.apply();
                            Intent divice = new Intent(MainActivity.this, BindActivity.class);
                            startActivity(divice);
                            finish();
                        } else if (loginBean.getCode() == 0 && n == 1) {
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, R.string.bind_success, Toast.LENGTH_SHORT).show();
                            edit.putBoolean("facebook", true);
                            edit.apply();
                            Intent facebook = new Intent(MainActivity.this, BindActivity.class);
                            startActivity(facebook);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, R.string.bind_field, Toast.LENGTH_SHORT).show();
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

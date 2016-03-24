package com.bmob.testapp;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnClickListener{
	
	EditText et_username, et_password;
	Button btn_login;
	TextView tv_register, tv_resetPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);
		
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		tv_register = (TextView) findViewById(R.id.tv_register);
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_resetPassword = (TextView) findViewById(R.id.tv_repassword);
		btn_login.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		tv_resetPassword.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_login:
			
			String username = et_username.getText().toString();
			String password = et_password.getText().toString();
			
			final BmobUser user = new BmobUser();
			user.setUsername(username);
			user.setPassword(password);
			user.login(this, new SaveListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					if(user.getEmailVerified()){
						Toast.makeText(LoginActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
					}else{
						// ��ʾ�û������˻�
					}
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this, "��¼ʧ�ܣ�"+arg1, Toast.LENGTH_SHORT).show();
				}
			});	
			
			break;
		case R.id.tv_register:
			startActivity(new Intent(this, RegisterActivity.class));	
			break;
		case R.id.btn_updateUser:
			
			MyUser myuser = BmobUser.getCurrentUser(this, MyUser.class);
			myuser.setNickName("������2");
			myuser.setAddress("������");
			myuser.update(this, new UpdateListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("�����û���Ϣ�ɹ�");
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			break;
		case R.id.tv_repassword:
			final String email = "2904194889@qq.com";
			BmobUser.resetPassword(this, email, new ResetPasswordListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this,"������������ɹ����뵽" + email + "��������������ò���",Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this,"��������ʧ��:",Toast.LENGTH_SHORT).show();
				}
			});
			break;
		
		default:
			break;
		}
	}
	
}

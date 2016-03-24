package com.bmob.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisterActivity extends Activity implements OnClickListener {
	
	EditText et_username, et_password;
	Button btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_register);
		
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();

		MyUser user = new MyUser();
		user.setEmail("2904194889@qq.com");
		user.setUsername(username);
		user.setPassword(password);
		user.setNickName("稻草人");
		user.setAge(23);
		user.setAddress("上海市");
		user.signUp(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(RegisterActivity.this, "注册失败："+arg1, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}

package com.bmob.testapp;

import cn.bmob.v3.Bmob;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// ≥ı ºªØBmobSDK
		Bmob.initialize(this, "19bf7d23563c99b2d712510fafa6724c");
		
	}
	
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
}

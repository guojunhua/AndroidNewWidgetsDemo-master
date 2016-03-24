package com.bmob.testapp;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.FindListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class TestGeoPointActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_geopoint);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_createGeopoint:
			
			break;
		case R.id.btn_queryGeopoint:
			
			BmobQuery<Footballer> query = new BmobQuery<Footballer>();
//			query.addWhereNear("location", new BmobGeoPoint(75.32483,39.490371));
			query.addWhereWithinKilometers("location", new BmobGeoPoint(75.32483,39.490371), 20);
			query.findObjects(this, new FindListener<Footballer>() {
				
				@Override
				public void onSuccess(List<Footballer> arg0) {
					// TODO Auto-generated method stub
					toast("查询成功："+arg0.size());
				}
				
				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					toast("查询失败："+arg1);
				}
			});
			
			break;

		default:
			break;
		}
	}

}

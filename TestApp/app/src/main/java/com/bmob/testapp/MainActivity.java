package com.bmob.testapp;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnClickListener {
	
	Footballer footballer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        findViewById(R.id.btn_createData).setOnClickListener(this);
        
    }

	@Override
	public void onClick(View arg0) {
		
		switch (arg0.getId()) {
		case R.id.btn_createData:
			// TODO Auto-generated method stub
			
			// 添加数据
			footballer = new Footballer();
			footballer.setName("梅西");
			footballer.setAge(28);
			footballer.setScore(88);
			footballer.save(this, new SaveListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("创建数据成功");
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					toast("创建数据失败："+arg1);
				}
			});
			
			break;
		case R.id.btn_updateData:
			
			// 更新数据
			footballer = new Footballer();
			footballer.setObjectId("0b1f5ca0f9");
			footballer.setName("梅西");
			footballer.setAge(25);
			footballer.setScore(80);
			footballer.update(this, new UpdateListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("更新数据成功");
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					toast("更新数据失败："+arg1);
				}
			});
			break;
		case R.id.btn_deleteData:
			
			// 删除数据
			footballer = new Footballer();
			footballer.setObjectId("c1ec4cef08");
			footballer.delete(this, new DeleteListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("删除数据成功");
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					toast("删除数据失败："+arg1);
				}
			});
			
			break;
		case R.id.btn_findData:
			BmobQuery<Footballer> query = new BmobQuery<Footballer>();
//			query.addWhereEqualTo("name", "梅西");
			query.addWhereWithinKilometers("location", new BmobGeoPoint(116.509478, 39.874413), 1.5);
			query.findObjects(this, new FindListener<Footballer>() {

				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					toast("查询数据失败："+arg1);
				}

				@Override
				public void onSuccess(List<Footballer> arg0) {
					// TODO Auto-generated method stub
					for (Footballer footballer : arg0) {
						toast("查询数据成功:"+footballer.getName()+"-- "+footballer.getAge());
					}
				}
			});
			
//			query.getObject(this, "d021581add", new GetListener<Footballer>() {
//				
//				@Override
//				public void onSuccess(Footballer footballer) {
//					// TODO Auto-generated method stub
//					toast("查询数据成功:"+footballer.getName()+"-- "+footballer.getAge());
//				}
//				
//				@Override
//				public void onFailure(int arg0, String arg1) {
//					// TODO Auto-generated method stub
//					toast("查询数据失败："+arg1);
//				}
//			});
			
			
			
			break;

		default:
			break;
		}
		
		
		
	}

}

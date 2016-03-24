package com.bmob.testapp;

import java.io.File;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class TestFileActivity extends BaseActivity implements OnClickListener {
	
	ImageView iv_icon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_file);
		iv_icon = (ImageView) findViewById(R.id.imageView1);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_upload:
			
			// 上传文件
			String picPath = "sdcard/Download/1403419576883.png";
			
			final BmobFile file = new BmobFile(new File(picPath));
			file.upload(this, new UploadFileListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					
					Footballer footballer = new Footballer();
					footballer.setName("雪糕校花");
					footballer.setAge(21);
					footballer.setScore(60);
					footballer.setIcon(file);
					footballer.save(TestFileActivity.this, new SaveListener() {
						
						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							toast("创建数据成功");
						}
						
						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
					});
					
				}
				
				@Override
				public void onProgress(Integer arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					toast("保存文件失败："+arg1);
				}
			});
			
			break;
		case R.id.btn_download:
			BmobQuery<Footballer> query = new BmobQuery<Footballer>();
			query.getObject(this, "55f9e5c116", new GetListener<Footballer>() {
				
				@Override
				public void onSuccess(Footballer arg0) {
					// TODO Auto-generated method stub
					BmobFile icon = arg0.getIcon();
//					String url = icon.getFileUrl();
					
					icon.loadImage(TestFileActivity.this, iv_icon);
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			break;
		case R.id.btn_loadimage:
			BmobQuery<Footballer> query2 = new BmobQuery<Footballer>();
			query2.getObject(this, "55f9e5c116", new GetListener<Footballer>() {
				
				@Override
				public void onSuccess(Footballer arg0) {
					// TODO Auto-generated method stub
					BmobFile icon = arg0.getIcon();
					icon.loadImageThumbnail(TestFileActivity.this, iv_icon, 100, 100, 100);
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			break;

		default:
			break;
		}
		
	}
	
}

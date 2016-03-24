package com.bmob.testapp.push;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bmob.testapp.BaseActivity;
import com.bmob.testapp.R;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TestBmobPushActivity extends BaseActivity implements OnClickListener{

	BmobPushManager pm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_push);
		pm = new BmobPushManager(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_initPush:
			// ���浱ǰ�豸��Ϣ
			BmobInstallation.getCurrentInstallation(this).save();
			// �������ͷ���
			BmobPush.startWork(this, "19bf7d23563c99b2d712510fafa6724c");
			break;
		case R.id.btn_subscribe:
			// ��������
			BmobInstallation install = BmobInstallation.getCurrentInstallation(this);
			install.subscribe("Giants");
			install.save();
			break;
		case R.id.btn_pushAllMsg:
			// �������ն����͵�һ����Ϣ��
			pm.pushMessageAll("�������ն����͵�һ����Ϣ��");
			break;
		case R.id.btn_pushMsgChannel:
			BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
			String[] names = {"Giants"};
			
			query.addWhereContainedIn("channels", Arrays.asList(names));
			pm.setQuery(query);
			pm.pushMessage("�����Giants�������͵�һ����Ϣ��");
			break;

		default:
			break;
		}
	}
	
}

package com.ht.htsdk_library.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ht.htsdk_library.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

	// 数据库名称
	private static String DB_NAME = "HTsdk_login.db";
	// 数据库版本
	private static int DB_VERSION = 2;
	private SQLiteDatabase db;
	private SqliteHelper dbHelper;

	public DataHelper(Context context) {
		dbHelper = new SqliteHelper(context, DB_NAME, null, DB_VERSION );
		db = dbHelper.getWritableDatabase();
	}

	public void Close() {
		db.close();
		dbHelper.close();
	}

	// 获取users表中的UserID、Access Token、Access Secret的记录
	public UserInfo GetUserInfo(String name) {
		UserInfo user = null;
		List<UserInfo> userList = new ArrayList<UserInfo>();
		Cursor cursor = db.rawQuery("select * from userinfo where name = ? ",new String[]{name});
		cursor.moveToFirst();
		//!cursor.isAfterLast() && (cursor.getString(1) != null )
		while (cursor.moveToNext()) {
			user = new UserInfo();
			user.setUid(cursor.getString(1));
			user.setName(cursor.getString(2));
			user.setToken(cursor.getString(3));
			user.setDivice(cursor.getInt(4));
			user.setFacebookinfo(cursor.getInt(5));
			user.setEmail(cursor.getInt(6));
			userList.add(user);
		}
		cursor.close();
		return user;
	}

	// 判断users表中的是否包含某个UserID的记录
	public Boolean HaveUserInfo(String UserId) {
		Boolean b = false;
		Cursor cursor = db.query(SqliteHelper. TB_NAME, null, UserInfo.NAME
				+ "=?", new String[]{UserId}, null, null, null );
		b = cursor.moveToFirst();
		Log. e("HaveUserInfo", b.toString());
		cursor.close();
		return b;
	}

	// 更新users表的记录，根据UserId更新用户昵称和用户图标
	public int UpdateUserInfo(String userName,String UserId) {
		ContentValues values = new ContentValues();
		values.put(UserInfo. NAME, userName);
		int id = db.update(SqliteHelper.TB_NAME, values, UserInfo.USERID + "=?", new String[]{UserId});
		Log. e("UpdateUserInfo2", id + "");
		return id;
	}

	// 更新users表的记录
	public int UpdateUserInfo(UserInfo user) {
		ContentValues values = new ContentValues();
		values.put(UserInfo. USERID, user.getUid());
		values.put(UserInfo. TOKEN, user.getToken());
		values.put(UserInfo. NAME, user.getName());
		values.put(UserInfo.DIVICE,user.getDivice());
		values.put(UserInfo.FACEBOOKINFO,user.getFacebookinfo());
		values.put(UserInfo.EMAIL,user.getEmail());
		int id = db.update(SqliteHelper.TB_NAME, values, UserInfo.USERID + "="
				+ user.getUid(), null);
		Log. e("UpdateUserInfo", id + "");
		return id;
	}

	// 添加users表的记录
	public Long SaveUserInfo(UserInfo user) {
		ContentValues values = new ContentValues();
		values.put(UserInfo. USERID, user.getUid());
		values.put(UserInfo. NAME, user.getName());
		values.put(UserInfo. TOKEN, user.getToken());
		values.put(UserInfo.DIVICE,user.getDivice());
		values.put(UserInfo.FACEBOOKINFO,user.getFacebookinfo());
		values.put(UserInfo.EMAIL,user.getEmail());
		Long uid = db.insert(SqliteHelper. TB_NAME, UserInfo.ID, values);
		Log. e("SaveUserInfo", uid + "");
		return uid;
	}

	// 删除users表的记录
	public int DelUserInfo(String UserId) {
		int id = db.delete(SqliteHelper. TB_NAME,
				UserInfo. USERID + "=?", new String[]{UserId});
		Log. e("DelUserInfo", id + "");
		return id;
	}

	public static UserInfo getUserByName(String userName,List<UserInfo> userList){
		UserInfo userInfo = null;
		int size = userList.size();
		for( int i=0;i<size;i++){
			if(userName.equals(userList.get(i).getName())){
				userInfo = userList.get(i);
				break;
			}
		}
		return userInfo;
	}
}

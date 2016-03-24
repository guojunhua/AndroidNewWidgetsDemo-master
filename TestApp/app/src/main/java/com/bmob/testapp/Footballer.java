package com.bmob.testapp;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 球员信息类
 *
 */
public class Footballer extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5398656422386992336L;
	
	// 球员的名称
	private String name;
	// 球员的年龄
	private int age;
	// 球员的分数
	private int score;
	// 图像
	private BmobFile icon;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public BmobFile getIcon() {
		return icon;
	}
	public void setIcon(BmobFile icon) {
		this.icon = icon;
	}
	
}

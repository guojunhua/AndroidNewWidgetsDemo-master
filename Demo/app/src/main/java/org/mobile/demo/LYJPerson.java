package org.mobile.demo;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 郭君华 on 2016/4/7.
 * Email：guojunhua3369@163.com
 */
@Table(name="lyj_person")
public class LYJPerson {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
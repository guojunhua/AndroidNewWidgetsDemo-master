package org.mobile.demo;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * Created by 郭君华 on 2016/4/7.
 * Email：guojunhua3369@163.com
 */
public class MyApp extends Application {
    private static volatile MyApp instance;
    public static MyApp getInstance() {
        if (instance == null) {
            synchronized (MyApp.class) {
                if (instance == null) {
                    instance = new MyApp();
                }
            }
        }

        return instance;
    }
    private DbManager.DaoConfig daoConfig;
    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        daoConfig = new DbManager.DaoConfig()
                .setDbName("lyj_db")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });//数据库更新操作
        DbManager db = x.getDb(daoConfig);
        LYJPerson person1=new LYJPerson();
        person1.setName("liyuanjinglyj");
        person1.setAge("23");
        LYJPerson person2=new LYJPerson();
        person2.setName("xutilsdemo");
        person2.setAge("56");
        try {
            db.save(person1);
            db.save(person2);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}

package org.mobile.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ImageView image;
    public TextView text;
    public String str = "　　\\r\\n<p><br /></p><p>    北京西美整形医院是由国家卫生部门正式批准的医疗美容整容机构，执业许可证号： 001532110102417119 倚擈礑邡霸翞姒霆，北京市医疗整形美容业协会理事单位。伮梔輢讦鈄开设整形美容、口腔美容、激光美容三大科室，每个科室都能做到“专”而“精”。　　</p><p>    先进设备　　</p><p>    美国体外超声吸脂机、瑞士共振吸脂机、意大利电子吸脂机、韩国脸形磨骨机、日本高端内窥镜系列设备、美国 BEYOND 冷光牙齿美白仪、意大利 MOCOM“B 级 ” 消毒设备、美国可以人激光设备、全自动高档手术台、麻醉机、监护仪等。</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = ((ImageView) findViewById(R.id.imager));
        text = ((TextView) findViewById(R.id.text));
        text.setText(Html.fromHtml(str));
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
                .setRadius(DensityUtil.dip2px(100))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                .setFailureDrawableId(R.mipmap.ic_launcher)//加载失败后默认显示图片
                .build();
        x.image().bind(image, "http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg",imageOptions);

        DbManager db = x.getDb(((MyApp)getApplicationContext()).getDaoConfig());
        try {
            List<LYJPerson> lyjPersons=db.selector(LYJPerson.class).findAll();
            for (int i=0;i<lyjPersons.size();i++){
                Log.e("liyuanjinglyj", "LYJPerson" + i + ".name=" + lyjPersons.get(i).getName());
                Log.e("liyuanjinglyj", "LYJPerson" + i + ".name=" + lyjPersons.get(i).getAge());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        x.http().get(getRequestParams("http://blog.csdn.net/mobile/experts.html"), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                text.setText(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(Callback.CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    public RequestParams getRequestParams(String url){
        RequestParams params = new RequestParams(url);
        return params;
    }
}

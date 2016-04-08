package org.mobile.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭君华 on 2016/4/7.
 * Email：guojunhua3369@163.com
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private List<String> numbers;
    private NumbersAdapter adapter;
    private PopupWindow popupWindow;
    private TextView textView;
   private LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = ((TextView) findViewById(R.id.et_number));
        findViewById(R.id.ib_down_arrow).setOnClickListener(this);
        ((Button) findViewById(R.id.hah)).setOnClickListener(this);
        dialog = new LoadingDialog(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ib_down_arrow){
            // 弹出选择号码对话框
            showSelectNumberDialog();
        }else if (v.getId()==R.id.hah){
            dialog.show();
        }
    }

    /**
     * 弹出选择号码对话框
     */
    private void showSelectNumberDialog() {
        numbers = getNumbers();

        ListView lv = new ListView(this);
        lv.setBackgroundResource(R.drawable.icon_spinner_listview_background);
        // 隐藏滚动条
        lv.setVerticalScrollBarEnabled(false);
        // 让listView没有分割线
        lv.setDividerHeight(0);
        lv.setDivider(null);
        lv.setOnItemClickListener(this);

        adapter = new NumbersAdapter();
        lv.setAdapter(adapter);

        popupWindow = new PopupWindow(lv, 400, 300);
        // 设置点击外部可以被关闭
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 设置popupWindow可以得到焦点
        popupWindow.setFocusable(true);

        popupWindow.showAsDropDown(textView, 2, 5);        // 显示

    }

    private List<String> getNumbers() {
        List<String> numbers = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            numbers.add("Animation" + i);
        }
        return numbers;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "ListView的第" + position + "个Item被点击了..", Toast.LENGTH_LONG).show();

        String number = numbers.get(position);
        textView.setText(number);

        popupWindow.dismiss();
    }

    class NumbersAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return numbers.size();
        }

        @Override
        public Object getItem(int position) {
            return numbers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NumberViewHolder mHolder = null;
            if (convertView == null) {
                mHolder = new NumberViewHolder();
                convertView = LayoutInflater.from(SecondActivity.this).inflate(R.layout.item_spinner_numbers, null);
                mHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
                mHolder.ibDelete = (ImageButton) convertView.findViewById(R.id.ib_delete);
                convertView.setTag(mHolder);
            } else {
                mHolder = (NumberViewHolder) convertView.getTag();
            }

            mHolder.tvNumber.setText(numbers.get(position));
            mHolder.ibDelete.setTag(position);
            mHolder.ibDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                    builder.setTitle("温馨提示");
                    builder.setMessage("确认是否删除");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int index = (Integer) v.getTag();
                            numbers.remove(index);
                            adapter.notifyDataSetChanged();

                            if (numbers.size() == 0) {
                                popupWindow.dismiss();
                            }
                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                }
            });

            return convertView;
        }
    }

    public class NumberViewHolder {
        public TextView tvNumber;
        public ImageButton ibDelete;
    }
}

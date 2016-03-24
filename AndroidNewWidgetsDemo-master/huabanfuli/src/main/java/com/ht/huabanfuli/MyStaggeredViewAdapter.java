package com.ht.huabanfuli;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭君华 on 2016/3/2.
 * Email：guojunhua3369@163.com
 */
public class MyStaggeredViewAdapter extends RecyclerView.Adapter<MyStaggeredViewAdapter.MyRecyclerViewHolder> {

    public Context mContext;
    public List<HuTuBean> Datas = new ArrayList<HuTuBean>();
    public LayoutInflater mLayoutInflater;

    public MyStaggeredViewAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<HuTuBean> list) {
        this.Datas = list;
        Log.e("---guo---", Datas.toString());
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.item_mains, parent, false);
        MyRecyclerViewHolder mViewHolder = new MyRecyclerViewHolder(mView);
        mViewHolder.mImg = ((ImageView) mView.findViewById(R.id.id_imageview));
        return mViewHolder;
    }

    /**
     * 绑定ViewHoler，给item中的控件设置数据
     */
    @Override
    public void onBindViewHolder(final MyRecyclerViewHolder holder, final int position) {
        Log.e("---guo---", Datas.get(position).getThumb());
        Log.e("hahhahah---", "jsdfajasja");
        Picasso.with(mContext).load(Datas.get(position).getThumb()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return Datas.size();
    }

    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        public MyRecyclerViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
    }
}

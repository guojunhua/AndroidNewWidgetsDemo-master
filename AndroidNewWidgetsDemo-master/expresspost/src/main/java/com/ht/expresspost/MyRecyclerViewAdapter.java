package com.ht.expresspost;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monkey on 2015/6/29.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public Context mContext;
    public List<ExpressTableBean.ShowapiResBodyEntity.ExpressListEntity> data = new ArrayList<ExpressTableBean.ShowapiResBodyEntity.ExpressListEntity>();
    public LayoutInflater mLayoutInflater;

    public MyRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<ExpressTableBean.ShowapiResBodyEntity.ExpressListEntity> data) {
        this.data = data;
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.item_main, parent, false);
        MyRecyclerViewHolder mViewHolder = new MyRecyclerViewHolder(mView);
        return mViewHolder;
    }

    /**
     * 绑定ViewHoler，给item中的控件设置数据
     */
    @Override
    public void onBindViewHolder(final MyRecyclerViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });

        }
        if (!TextUtils.isEmpty(data.get(position).getImgUrl())) {
            Picasso.with(mContext).load(data.get(position).getImgUrl()).into(holder.loge);
        } else {
            holder.loge.setImageResource(R.drawable.aol);
        }
        holder.expName.setText("公司名称："+data.get(position).getExpName());
        holder.simpleName.setText("公司简称："+data.get(position).getSimpleName());
        holder.phone.setText("服务电话："+data.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }
}

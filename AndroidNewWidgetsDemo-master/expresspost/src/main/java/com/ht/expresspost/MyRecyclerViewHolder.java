package com.ht.expresspost;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Monkey on 2015/6/29.
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView expName,simpleName,phone;
    public ImageView loge;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        loge = ((ImageView) itemView.findViewById(R.id.logo));
        expName = ((TextView) itemView.findViewById(R.id.expName));
        simpleName = ((TextView) itemView.findViewById(R.id.simpleName));
        phone = ((TextView) itemView.findViewById(R.id.phone));
    }
}

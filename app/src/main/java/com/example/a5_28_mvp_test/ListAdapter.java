package com.example.a5_28_mvp_test;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydata.ListBean;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Context context;
    List<ListBean.DatasBean> datas=new ArrayList<>();

    public void setDatas(List<ListBean.DatasBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
    public void setRefreshDatas(List<ListBean.DatasBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
    public ListAdapter(Context context) {
        this.context = context;
    }


//    public ListAdapter(Context context, List<ListBean.DatasBean> datas) {
//        this.context = context;
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(datas.get(position).getThumbnail()).into(holder.iv_photo);
        holder.tv_text.setText(datas.get(position).getAuthor());
        holder.tv_title.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo;
        TextView tv_title;
        TextView tv_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo=itemView.findViewById(R.id.iv_photo);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_text=itemView.findViewById(R.id.tv_text);
        }
    }
}

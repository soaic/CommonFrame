package com.commonframe.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.commonframe.R;
import com.greendao.bean.User;

import java.util.List;

/**
 * Created by XiaoSai on 2016/12/8.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private List<User> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    
    public RecyclerViewAdapter(Context context, List<User> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_green_dao, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){
        holder.tv.setText( mDatas.get(position).getUserName());
    }

    @Override
    public int getItemCount(){
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv=(TextView) view.findViewById(R.id.id_name);
        }

    }
}

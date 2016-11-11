package com.commonframe.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.commonframe.R;
import com.commonframe.mvp.entity.PostQueryInfo;

import java.util.List;

/**
 * 
 * Created by XiaoSai on 2016/11/10.
 */

public class LogisticsAdapter extends BaseAdapter{
    private List<PostQueryInfo.DataBean> datas;
    private LayoutInflater inflater;
    public LogisticsAdapter(Context context,List<PostQueryInfo.DataBean> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int i,View view,ViewGroup viewGroup) {
        PostQueryInfo.DataBean data = datas.get(i);
        if(view == null)
            view = inflater.inflate(R.layout.view_item_logistics, null);
        TextView tv_content= (TextView) view.findViewById(R.id.tv_conent);
        TextView tv_date= (TextView) view.findViewById(R.id.tv_date);
        tv_content.setText(data.getContext().replace("[","【").replace("]","】"));
        tv_date.setText(data.getTime());
        return view;
    }
    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }
    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
}

package com.commonframe.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.commonframe.R;
import com.commonframe.base.BaseActivity;
import com.greendao.bean.User;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemoActivity extends BaseActivity{
    private SRecyclerView recyclerView;
    private CommonAdapter<User> commonAdapter;
    private List<User> mDatas= new ArrayList<>();
    private LinearLayoutManager layoutManager;
    @Override
    protected void initVariables(Bundle savedInstanceState){
        
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setTopTitleText("RecyclerViewDemoActivity");
        setContentView(R.layout.activity_recycler_view_demo);
        
        recyclerView = getViewById(R.id.recycler_view);
        //设置布局管理器
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView通用adapter
        commonAdapter = new CommonAdapter<User>(this,R.layout.item_green_dao,mDatas){
            @Override
            protected void convert(ViewHolder holder,User user,int position){
                holder.setText(R.id.id_name,user.getUserName());
            }
        };

        recyclerView.setAdapter(commonAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setRefreshListener(new SRecyclerView.RefreshListener(){
            @Override
            public void onRefresh(){
                requestData(1);
            }
        });
        recyclerView.setRefreshProgressStyle(ProgressStyle.SquareSpin);
    }

    @Override
    protected void loadData(){
        recyclerView.startRefresh();
    }
    
    private void requestData(final int state){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(state == 1){
                    mDatas.clear();
                }
                for(int i=0;i<20;i++){
                    mDatas.add(new User(null,"userName="+i));
                }
                commonAdapter.notifyDataSetChanged();
                if(state == 1){
                    recyclerView.refreshComplete();
                }else{
                    recyclerView.loadMoreComplete();
                }
                
            }
        }, 500);
    }
}

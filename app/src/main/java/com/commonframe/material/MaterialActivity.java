package com.commonframe.material;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.commonframe.R;
import com.commonframe.mvp.ui.adapter.RecyclerViewAdapter;
import com.commonlibrary.ToastUtils;
import com.greendao.bean.User;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity{
    private RecyclerView recyclerview;
//    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerViewAdapter adapter;
    private CommonAdapter<User> commonAdapter;
    private LoadMoreWrapper<User> loadMoreWrapper;
    private EmptyWrapper mEmptyWrapper;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private List<User> mDatas= new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private int lastVisibleItem;
    private Handler handler = new Handler();
    private boolean isRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        initActionBar();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(view,"Replace with your own action",Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }
        });

        for(int i=0;i<20;i++){
            mDatas.add(new User(null,"userName="+i));
        }

        recyclerview = (RecyclerView)findViewById(R.id.recyclerViewTest);
        //设置布局管理器
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        //recyclerView通用adapter
        commonAdapter = new CommonAdapter<User>(this,R.layout.item_green_dao,mDatas){
            @Override
            protected void convert(ViewHolder holder,User user,int position){
                holder.setText(R.id.id_name,user.getUserName());
            }
        };

        //设置头部和尾部
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_material_heater_or_footer,recyclerview,false);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_material_heater_or_footer,recyclerview,false);
        mHeaderAndFooterWrapper.addHeaderView(view);
        //不能同时设置loadMoreView
        //mHeaderAndFooterWrapper.addFootView(view2);

        //设置上拉自动加载
        loadMoreWrapper = new LoadMoreWrapper<>(mHeaderAndFooterWrapper);
        loadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener(){
            @Override
            public void onLoadMoreRequested(){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<10;i++){
                            mDatas.add(new User(null,"userName="+i));
                        }
                        loadMoreWrapper.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });




        recyclerview.setAdapter(loadMoreWrapper);
        
        //设置recyclerView item的点击和长按
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view,RecyclerView.ViewHolder holder,int position){
                ToastUtils.showShortToast(getApplicationContext(),"当前点击="+position);   
            }

            @Override
            public boolean onItemLongClick(View view,RecyclerView.ViewHolder holder,int position){
                ToastUtils.showShortToast(getApplicationContext(),"当前长按="+position);
                return true;
            }
        });
        
        
        //设置Item增加、移除动画
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    public void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //这行要前面调用

        //toolbar.setLogo(R.mipmap.ic_launcher);//设置logo
        setTitle("Material Design");//设置标题
        toolbar.setSubtitle("Design");//设置副标题，在标题下面
        toolbar.setNavigationIcon(R.drawable.icon_back); //设置返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

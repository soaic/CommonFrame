package com.commonframe.greendao;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.commonframe.R;
import com.commonframe.mvp.ui.adapter.RecyclerViewAdapter;
import com.greendao.bean.User;
import com.greendao.dao.UserDao;
import com.greendao.service.UserService;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * GreenDao ORM框架Demo
 */
public class GreenDaoDemoActivity extends Activity{
    private RecyclerView recyclerview;
    private List<User> mDatas = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private EditText user_name;
    private Button add_user,update_user,delete_user,search_user;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_demo);
        
        recyclerview = (RecyclerView)findViewById(R.id.recylerview);
        //设置布局管理器
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this,mDatas);
        recyclerview.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        user_name = (EditText)findViewById(R.id.user_name);
        add_user = (Button)findViewById(R.id.add_user);
        add_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                insert();
            }
        });

        update_user = (Button)findViewById(R.id.update_user);
        update_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                update();
            }
        });

        delete_user = (Button)findViewById(R.id.delete_user);
        delete_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                delete();
            }
        });

        search_user  = (Button)findViewById(R.id.search_user);
        search_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                searchByName();
            }
        });
        
        search();
    }

    /**
     * 添加
     */
    public void insert(){
        String userName = user_name.getText().toString();
        if(TextUtils.isEmpty(userName)){
           return; 
        }
        User user = new User(null,userName);

        //UserService.getInstance().save(user);
        //UserService.getInstance().getDao().save(user);
        UserService.getInstance().getRxDao().insert(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User note) {
                        search();
                    }
                });
    }

    /**
     * ID降序查询所有数据
     */
    public void search() {
        //List<User> users = UserService.getInstance().getQueryBuilder().orderDesc(UserDao.Properties.Id).build().list();
        UserService.getInstance().getQueryBuilder().orderDesc(UserDao.Properties.Id).rx().list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>(){
                    @Override
                    public void call(List<User> users){
                        mDatas.clear();
                        for(int i = 0;i<users.size();i++){
                            User user = users.get(i);
                            mDatas.add(user);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 用Rx模式模糊查询
     */
    public void searchByName() {
        String userName = user_name.getText().toString();
        if(TextUtils.isEmpty(userName)){
            return;
        }
        //List<User> users = UserService.getInstance().getDao().queryBuilder().where(UserDao.Properties.Name.like("%"+userName+"%")).build().list();
        //List<User> users = UserService.getInstance().getDao().queryBuilder().where(new WhereCondition.PropertyCondition(UserDao.Properties.Name, " LIKE ?", "%"+userName+"%")).build().list();
        UserService.getInstance().getQueryBuilder().where(new WhereCondition.PropertyCondition(UserDao.Properties.UserName, " LIKE ?", "%"+userName+"%")).rx().list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>(){
                    @Override
                    public void call(List<User> users){
                        mDatas.clear();
                        for(int i = 0;i<users.size();i++){
                            User user = users.get(i);
                            mDatas.add(user);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 删除
     */
    public void delete(){
        if(mDatas.size()<=0){
            return;
        }
        User user = mDatas.get(0);
        //UserService.getInstance().delete(user);
        //UserService.getInstance().getDao().delete(user);
        UserService.getInstance().getRxDao().delete(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>(){
                    @Override
                    public void call(Void aVoid){
                        search();
                    }
                });
    }

    /**
     * 修改
     */
    public void update(){
        String userName = user_name.getText().toString();
        if(TextUtils.isEmpty(userName)){
            return;
        }
        User user = mDatas.get(0);
        user.setUserName(userName);
        //UserService.getInstance().update(user);
        //UserService.getInstance().getDao().update(user);
        UserService.getInstance().getRxDao().update(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>(){
                    @Override
                    public void call(User user){
                        search();
                    }
                });
    }
}

package com.commonframe.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.commonframe.R;
import com.commonframe.base.BaseActivity;

public class FragmentActivity extends BaseActivity{
    private BottomNavigationBar bottom_navigation_bar;
    private BadgeItem numberBadgeItem;
    private TestFragment fragment1;
    private TestFragment fragment2;
    private TestFragment fragment3;
    private TestFragment fragment4;
    
    @Override
    protected void initVariables(Bundle savedInstanceState){
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.activity_fragment);
        //隐藏头部
        hideTopView();
        
        bottom_navigation_bar = getViewById(R.id.bottom_navigation_bar);

        numberBadgeItem = new BadgeItem()
                .setText("1")
                .setHideOnSelect(false);
        
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_FIXED);
        bottom_navigation_bar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottom_navigation_bar.setInActiveColor(R.color.gray);
        bottom_navigation_bar.setActiveColor(R.color.orange);
        
        bottom_navigation_bar
                .addItem(new BottomNavigationItem(R.drawable.icon_biaoqianlan_qiyequan02, "企业").setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_biaoqianlan_shangcheng02, "商城"))
                .addItem(new BottomNavigationItem(R.drawable.icon_biaoqianlan_xiaoxi02, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.icon_biaoqianlan_wode02, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
        bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position){
                tabSelect(position);
            }

            @Override
            public void onTabUnselected(int position){
            }

            @Override
            public void onTabReselected(int position){
            }
        });
        tabSelect(0);
    }

    @Override
    protected void loadData(){
    }
    
    private void tabSelect(int position){
        //开启事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (fragment1 == null) {
                    fragment1 = TestFragment.newInstance("位置");
                }
                transaction.replace(R.id.fragment_fl_content, fragment1);
                break;
            case 1:
                if (fragment2 == null) {
                    fragment2 = TestFragment.newInstance("发现");
                }
                transaction.replace(R.id.fragment_fl_content, fragment2);
                break;
            case 2:
                if (fragment3 == null) {
                    fragment3 = TestFragment.newInstance("爱好");
                }
                transaction.replace(R.id.fragment_fl_content, fragment3);
                break;
            case 3:
                if (fragment4 == null) {
                    fragment4 = TestFragment.newInstance("图书");
                }
                transaction.replace(R.id.fragment_fl_content, fragment4);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }
}

package com.commonframe.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

import com.commonlibrary.LogUtils;
import com.jcodecraeer.xrecyclerview.AppBarStateChangeListener;
import com.jcodecraeer.xrecyclerview.ProgressStyle;

/**
 * TODO
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/4/18.
 */

public class SRecyclerView extends RecyclerView{
    //当前刷新监听
    private RefreshListener mRefreshListener;
    //当前加载监听
    private LoadMoreListener mLoadMoreListener;
    private int mRefreshProgressStyle = ProgressStyle.SysProgress;
    private SRecyclerViewRefreshHeader mRefreshHeader;
    private float mLastY = -1;
    private boolean pullRefreshEnabled = true;
    private AppBarStateChangeListener.State appbarState = AppBarStateChangeListener.State.EXPANDED;

    private static final float DRAG_RATE = 3;
    
    public SRecyclerView(Context context){
        super(context);
        init(context);
    }

    public SRecyclerView(Context context,@Nullable AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    public SRecyclerView(Context context,@Nullable AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init(context);
    }
    
    private void init(Context context){
        if (pullRefreshEnabled) {
            mRefreshHeader = new SRecyclerViewRefreshHeader(getContext());
            mRefreshHeader.setProgressStyle(mRefreshProgressStyle);
        }
    }

    @Override
    public void setAdapter(Adapter adapter){
        super.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (isOnTop() && pullRefreshEnabled && appbarState == AppBarStateChangeListener.State.EXPANDED) {
                    LogUtils.e("recycler","deltaY2="+(deltaY / DRAG_RATE));
                    mRefreshHeader.onMove(deltaY / DRAG_RATE);
                    if (mRefreshHeader.getVisibleHeight() > 0 && mRefreshHeader.getState() < SRecyclerViewRefreshHeader.STATE_REFRESHING) {
                        LogUtils.e("recycler","deltaY3");    
                        return false;
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if (isOnTop() && pullRefreshEnabled && appbarState == AppBarStateChangeListener.State.EXPANDED) {
                    if (mRefreshHeader.releaseAction()) {
                        if (mRefreshListener != null) {
                            mRefreshListener.onRefresh();
                        }
                    }
                }
                break;
        }
        
        
        
        
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //解决和CollapsingToolbarLayout冲突的问题
        AppBarLayout appBarLayout = null;
        ViewParent p = getParent();
        while (p != null) {
            if (p instanceof CoordinatorLayout) {
                break;
            }
            p = p.getParent();
        }
        if(p instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout)p;
            final int childCount = coordinatorLayout.getChildCount();
            for (int i = childCount - 1; i >= 0; i--) {
                final View child = coordinatorLayout.getChildAt(i);
                if(child instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout)child;
                    break;
                }
            }
            if(appBarLayout != null) {
                appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
                    @Override
                    public void onStateChanged(AppBarLayout appBarLayout, State state) {
                        appbarState = state;
                    }
                });
            }
        }
    }

    private boolean isOnTop() {
        LogUtils.e("recycler","mRefreshHeader.getParent()="+mRefreshHeader.getParent());
        if (mRefreshHeader.getParent() != null) {
            return true;
        } else {
            return false;
        }
    }

    private void updateHeaderHeight(float delta){
        
    }
    
    private void resetHeaderHeight(){
        
    }

    /**
     * 启动刷新
     */
    public void startRefresh(){
        if (pullRefreshEnabled && mRefreshListener != null) {
            mRefreshHeader.setState(SRecyclerViewRefreshHeader.STATE_REFRESHING);
            mRefreshListener.onRefresh();
        }
    }

    /**
     * 刷新完成
     */
    public void refreshComplete(){
        mRefreshHeader.refreshComplete();
    }

    /**
     * 加载完成
     */
    public void loadMoreComplete(){
        
    }

    /**
     * 设置是否启用加载
     * @param enabled true启动 false禁用
     */
    public void setLoadingMoreEnabled(boolean enabled){
        
    }

    /**
     * 设置是否启用刷新
     * @param enabled true启动 false禁用
     */
    public void setRefreshEnabled(boolean enabled){
        
    }

    /**
     * 设置刷新进度条
     * @param style 加载样式
     */
    public void setRefreshProgressStyle(int style){
        
    }

    /**
     * 设置加载进度条
     * @param style 加载样式
     */
    public void setLoadingMoreProgressStyle(int style){
        
    }

    /**
     * 设置刷新完成监听
     */
    public void setRefreshListener(RefreshListener listener){
        mRefreshListener = listener;
    }

    /**
     * 设置加载完成监听
     */
    public void setLoadMoreListener(LoadMoreListener listener){
        mLoadMoreListener = listener;
    }
    
    public interface RefreshListener{
        void onRefresh();
    }
    
    public interface LoadMoreListener{
        void onLoadMore();
    }
    
    
}

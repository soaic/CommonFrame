package com.commonframe.mvp.presenter;

import com.commonframe.base.MVPBasePresenter;
import com.commonframe.mvp.entity.PostQueryInfo;
import com.commonframe.mvp.model.PostSearchModelImpl;
import com.commonframe.mvp.model.impl.PostSearchModel;
import com.commonframe.mvp.view.MainView;

/**
 * Created by XiaoSai on 2016/11/10.
 */

public class PostPresenter extends MVPBasePresenter<MainView>{
    private PostSearchModel postSearchModel;
    public PostPresenter(MainView mainView){
        attach(mainView);
        postSearchModel = new PostSearchModelImpl();
    }
    public void requestHomeData(String type,String postid){
        if(postSearchModel == null||mView == null)
            return;
        mView.showProgressDialog();
        postSearchModel.requestPostSearch(type, postid, new PostSearchModel.PostSearchCallback() {
            @Override
            public void requestPostSearchSuccess(PostQueryInfo postQueryInfo) {
                mView.hideProgressDialog();
                if(postQueryInfo!=null&&"ok".equals(postQueryInfo.getMessage())) {
                    mView.updateListUI(postQueryInfo);
                }
            }
            @Override
            public void requestPostSearchFail(String failStr) {
                mView.hideProgressDialog();
                mView.errorToast(failStr);
            }
        });
    }
}

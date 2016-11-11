package com.commonframe.mvp.view;

import com.commonframe.base.BaseView;
import com.commonframe.mvp.entity.PostQueryInfo;

/**
 * Created by XiaoSai on 2016/11/10.
 */

public interface MainView extends BaseView{
    void updateListUI(PostQueryInfo postQueryInfo);
    void errorToast(String message);
}

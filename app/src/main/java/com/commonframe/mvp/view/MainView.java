package com.commonframe.mvp.view;

import com.commonframe.base.MVPBaseView;
import com.commonframe.mvp.entity.PostQueryInfo;

/**
 * Created by XiaoSai on 2016/11/10.
 */

public interface MainView extends MVPBaseView{
    void updateListUI(PostQueryInfo postQueryInfo);
    void errorToast(String message);
}

package com.commonframe.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.commonframe.R;
import com.commonframe.base.BaseFragment;

/**
 * Created by XiaoSai on 2017/2/27.
 */
public class TestFragment extends BaseFragment{
    private TextView test_tv_text;
    private String textStr;
    
    public static TestFragment newInstance(String param1) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected void initVariables(Bundle savedInstanceState){
        Bundle bundle = getArguments();
        textStr = bundle.getString("agrs1");
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.fragment_test);
        showTopView();
        showLoadingView();
        
        
        test_tv_text = getViewById(R.id.test_tv_text);
        test_tv_text.setText(textStr);
        setTopTitleText(textStr);
        
    }

    @Override
    protected void loadData(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                handleRequestError(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run(){
                                hideLoadingView();
                            }
                        },1000);
                    }
                });
            }
        },1000);
    }

    @Override
    protected void onUserVisible(){

    }
    
}

package com.commonframe.baseview;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.commonframe.App;
import com.commonframe.R;
import com.commonframe.base.BaseActivity;
import com.commonframe.net.OkHttpResponseListener;
import com.commonframe.net.RequestClient;
import com.commonframe.ui.SelectPhotoPop;
import com.imagelibrary.photoselect.CameraCore;
import com.imagelibrary.photoselect.CameraProxy;
import com.netlibrary.NetClient;
import com.netlibrary.listener.OnResultListener;

import net.bither.util.NativeUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseViewActivity extends BaseActivity implements CameraCore.CameraResult{
    
    private TextView select_photo;

    private CameraProxy cameraProxy;

    @Override
    protected void initVariables(Bundle savedInstanceState){
        cameraProxy = new CameraProxy(this, this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.activity_base_view);
        setTopTitleText("BaseView Demo");

        select_photo = getViewById(R.id.select_photo);
        select_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SelectPhotoPop.getInstance().showSelectPhotoPop(BaseViewActivity.this,view,new SelectPhotoPop.OnSelectTypeListener(){
                    @Override
                    public void onSelectCamera(PopupWindow selectPhotoPop){
                        String path = SelectPhotoPop.getInstance().getDiskCacheDir(BaseViewActivity.this)+"/temp.jpg";
                        Log.d("camera","path="+path);
                        cameraProxy.getPhoto2Camera(path);
                    }

                    @Override
                    public void onSelectAlbum(PopupWindow selectPhotoPop){
                        cameraProxy.getPhoto2Album();
                    }

                    @Override
                    public void onSelectCancel(PopupWindow selectPhotoPop){
                    }
                });
            }
        });
    }

    @Override
    protected void loadData(){

        new RequestClient.Builder()
                .baseUrl("https://test.gongyebangshou.com:8080/")
                .url("usr_verflogin.php")
                .param("phone","13113613681")
                .param("login_type","2")
                .param("verfcode","1234")
                .builder()
                .get(new OkHttpResponseListener<String>(){
                    @Override
                    public void onSuccess(String content){
                        Log.d("test","content="+content);
                    }

                    @Override
                    public void onFailure(Throwable err){

                    }
                });
    }

    @Override
    public void onCameraSuccess(final String filePath){

        new Thread(new Runnable(){
            @Override
            public void run(){
                Log.d("camera","filePath="+filePath);
                String path = SelectPhotoPop.getInstance().getDiskCacheDir(BaseViewActivity.this)+"/1_10000524.jpg";
                NativeUtil.compressBitmap(NativeUtil.getBitmapFromFile(filePath), path);
                new RequestClient.Builder()
                        .baseUrl("https://test.gongyebangshou.com:8080/")
                        .url("usr_uploadicon.php")
                        .param("type","1")
                        .file("data",new File(path))
                        .builder()
                        .postUpload(new OkHttpResponseListener<String>(){
                            @Override
                            public void onSuccess(String content){
                                Log.d("test","content="+content);
                            }

                            @Override
                            public void onFailure(Throwable err){

                            }
                        });
            }
        }).start();
    }

    @Override
    public void onCameraFail(String message){
        Log.d("camera","message="+message);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        cameraProxy.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState,PersistableBundle outPersistentState){
        super.onSaveInstanceState(outState,outPersistentState);
        cameraProxy.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        cameraProxy.onResult(requestCode, resultCode, data);
    }

}

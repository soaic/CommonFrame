package com.commonframe.socket;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.commonframe.IndexActivity;
import com.commonframe.R;
import com.commonframe.base.BaseActivity;
import com.commonlibrary.ToastUtils;
import com.socketlib.IReceiverListener;
import com.socketlib.SocketConnection;

import java.io.UnsupportedEncodingException;

public class SocketDemoActivity extends BaseActivity{
    private Button socket_test_btn;
    private EditText socket_content_et,socket_result_et;
    private SocketConnection socketConnection;
    private Handler handler = new Handler();
    
    @Override
    protected void initVariables(Bundle savedInstanceState){
        new Thread(new Runnable(){
            @Override
            public void run(){
                socketConnection = new SocketConnection("192.168.0.37",60000);
                boolean isConnect = socketConnection.connect();
                if(isConnect){
                    handler.post(new Runnable(){
                        @Override
                        public void run(){
                            ToastUtils.showShortToast(SocketDemoActivity.this,"连接成功");
                        }
                    });
                    socketConnection.setReceiverListener(new IReceiverListener(){
                        @Override
                        public void onReceiver(final byte[] data){
                            handler.post(new Runnable(){
                                @Override
                                public void run(){
                                    String str;
                                    try{
                                        str = socket_result_et.getText()+new String(data,"gbk");
                                    }catch(UnsupportedEncodingException e){
                                        e.printStackTrace();
                                        str = socket_result_et.getText()+new String(data);
                                    }
                                    socket_result_et.setText(str);
                                }
                            });
                        }
                    });
                }
            }
        }).start();
        
    }

    @Override
    protected void initViews(Bundle savedInstanceState){
        setContentView(R.layout.activity_socket_demo);
        
        setTopTitleText("Socket 测试");
        socket_test_btn = getViewById(R.id.socket_test_btn);
        socket_content_et = getViewById(R.id.socket_content_et);
        socket_result_et = getViewById(R.id.socket_result_et);

        socket_test_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                byte[] data;
                try{
                    data = socket_content_et.getText().toString().getBytes("gbk");
                }catch(UnsupportedEncodingException e){
                    e.printStackTrace();
                    data = socket_content_et.getText().toString().getBytes();
                }
                socketConnection.sendDataAsync(data);
            }
        });
    }

    @Override
    protected void loadData(){
        
    }
}

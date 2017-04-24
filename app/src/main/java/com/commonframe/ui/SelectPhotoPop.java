package com.commonframe.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.commonframe.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * TODO 选择图片
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/3/6.
 */

public class SelectPhotoPop{

    private View selectPhotoView;
    private TextView cameraView,albumView,cancelView;
    private PopupWindow selectPhotoPop;
    
    public static SelectPhotoPop getInstance(){
        return SingleLoader.INSTANCE;
    } 
    
    private static class SingleLoader{
        private final static SelectPhotoPop INSTANCE = new SelectPhotoPop();
    }
    
    public void showSelectPhotoPop(final Context context,View v,final OnSelectTypeListener listener) {
        if(selectPhotoPop == null){
            selectPhotoView = View.inflate(context,R.layout.pop_photo_select,null);
            albumView = (TextView)selectPhotoView.findViewById(R.id.pop_photo_gallery);
            cameraView = (TextView)selectPhotoView.findViewById(R.id.pop_photo_camera);
            cancelView = (TextView)selectPhotoView.findViewById(R.id.pop_photo_cancel);

            selectPhotoPop = new PopupWindow(selectPhotoView);
            //点击其它区域监听
            selectPhotoPop.setTouchInterceptor(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v,MotionEvent event){
                    if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
                        selectPhotoPop.dismiss();
                        return true;
                    }
                    return false;
                }
            });

            selectPhotoPop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
            selectPhotoPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            selectPhotoPop.setTouchable(true);
            selectPhotoPop.setFocusable(true);
            selectPhotoPop.setOutsideTouchable(true);
            selectPhotoPop.setBackgroundDrawable(new ColorDrawable());
            // 动画效果 从底部弹起
            selectPhotoPop.setAnimationStyle(R.style.AnimationBottomDialog);
        }
        //屏幕变暗
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = 0.7f;
        ((Activity)context).getWindow().setAttributes(lp);

        //popupWindow关闭监听
        selectPhotoPop.setOnDismissListener(new PopupWindow.OnDismissListener(){

            @Override
            public void onDismiss(){
                WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
                lp.alpha = 1f;
                ((Activity)context).getWindow().setAttributes(lp);
            }
        });
        //拍照
        cameraView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0){
                if(selectPhotoPop != null && selectPhotoPop.isShowing()){
                    selectPhotoPop.dismiss();
                }
                listener.onSelectCamera(selectPhotoPop);
            }
        });
        //从相册选择图片
        albumView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0){
                if(selectPhotoPop != null && selectPhotoPop.isShowing()){
                    selectPhotoPop.dismiss();
                }
                listener.onSelectAlbum(selectPhotoPop);
            }
        });
        //取消
        cancelView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(selectPhotoPop != null && selectPhotoPop.isShowing()){
                    selectPhotoPop.dismiss();
                }
                listener.onSelectCancel(selectPhotoPop);
            }
        });

        selectPhotoPop.showAtLocation(v,Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,0,0);
    }

    /**
     * 选择图片对话框接口
     */
    public interface OnSelectTypeListener{
        void onSelectCamera(PopupWindow selectPhotoPop);
        void onSelectAlbum(PopupWindow selectPhotoPop);
        void onSelectCancel(PopupWindow selectPhotoPop);
    }


    /**
     * 获取缓存目录
     * @param context
     * @return
     */
    public String getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}

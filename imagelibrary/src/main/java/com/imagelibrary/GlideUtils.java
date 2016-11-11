package com.imagelibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.Arrays;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by XS on 2016/7/14.
 */
public class GlideUtils{
    
    private volatile static GlideUtils mInstance;

    public static GlideUtils getInstance()
    {
        if (mInstance == null)
        {
            synchronized (GlideUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new GlideUtils();
                }
            }
        }
        return mInstance;
    }
    
    

    public void displayImage(Context context,ImageView imageView,String url){
        Glide.with(context)
                .load(url)                                          //加载资源
                .thumbnail(0.1f)                                    //用原图的1/10作为缩略图,如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示
                //.thumbnail(getDrawableRequestBuilder(context,0))    //本地资源作为缩略图
                .centerCrop()                                       //设置scaleType
                .placeholder(null)                                  //设置资源加载过程中的占位Drawable。
                .crossFade()                                        //设置加载渐变动画
                .priority(Priority.NORMAL)                          //指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW。默认为Priority.NORMAL。                                  
                .fallback(null)                                     //设置model为空时要显示的Drawable。如果没设置fallback，model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
                .error(null)                                        //设置load失败时显示的Drawable。
                .listener(listener)                                 //请求监听
                .skipMemoryCache(true)                              //设置跳过内存缓存，但不保证一定不被缓存（比如请求已经在加载资源且没设置跳过内存缓存，这个资源就会被缓存在内存中）。
                .diskCacheStrategy(DiskCacheStrategy.RESULT)        //缓存策略DiskCacheStrategy.SOURCE：缓存原始数据，DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据，DiskCacheStrategy.NONE：什么都不缓存，DiskCacheStrategy.ALL：缓存SOURC和RESULT。默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。
                .bitmapTransform(new CropCircleTransformation(context))  //圆角裁切
                .into(imageView);                                   //目标View
    }
    
    public String getSDSource(String fullPath){
        return "file://"+ fullPath;
    }

    public String getAssetsSource(String fileName){
        return "file:///android_asset/"+fileName;
    }

    public String getRawSource(Context context,int rawRid){
        return "android.resource://"+context.getPackageName()+"/raw/"+rawRid;
    }

    public String getDrawableSource(Context context,int drawRid){
        return "android.resource://"+context.getPackageName()+"/drawable/"+drawRid;
    }


    RequestListener<String,GlideDrawable> listener = new RequestListener<String,GlideDrawable>(){
        @Override
        public boolean onException(Exception e,String model,Target<GlideDrawable> target,boolean isFirstResource){
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource,String model,Target<GlideDrawable> target,boolean isFromMemoryCache,boolean isFirstResource){

            return false;
        }
    };

    /**
     * 获取 RequestBuilder
     * @param context
     * @param source
     * @return
     */
    public <T> DrawableRequestBuilder<T>  getDrawableRequestBuilder(Context context,T source){
        return Glide.with(context).load(source);
    }
    
    public <T> void displayImageView(DrawableRequestBuilder<T> builder, ImageView imageView){
        builder.into(imageView);
    }

    /**
     * 对图片进行裁剪、模糊、滤镜等处理： 
     */
    private void bitmapTransform(Context context,ImageView imageView,String imageUrl){
        //圆形裁剪
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
        
        //圆角处理
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(imageView);
        
        //灰度处理
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new GrayscaleTransformation(context))
                .into(imageView);
    }



    /**
     * 清除内存缓存 必须在UI线程中调用
     * @param context
     */
    public void clearMemory(Context context){
        Glide.get(context).clearMemory();

    }

    /**
     * 清除磁盘缓存 
     * 必须在后台线程中调用，建议同时clearMemory()
     * @param context
     */
    public void clearDiskCache(Context context){
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清除View缓存
     * @param view
     */
    public void clearViewCache(View view){
        Glide.clear(view);
    }

    /**
     * 获取Data下缓存大小
     * @param context
     * @param textView
     */
    public void getDataDiskCacheSize(Context context,TextView textView){
        new DiskCacheSizeTask(textView).execute(new File(context.getCacheDir(),DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
    }

    /**
     * 获取SD卡下缓存大小
     * @param context 
     * @param textView 要显示的view
     */
    public void getSDDiskCacheSize(Context context,TextView textView){
        new DiskCacheSizeTask(textView).execute(new File(context.getExternalCacheDir(),DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
    }

    
}

package com.imagelibrary;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.imagelibrary.glide.DiskCacheSizeTask;

import java.io.File;

import jp.wasabeef.glide.transformations.*;

/**
 Glide.with(context)
     .load(url)                                          //加载资源
     .thumbnail(0.1f)                                    //用原图的1/10作为缩略图,如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示
     .thumbnail(getDrawableRequestBuilder(context,0))    //本地资源作为缩略图
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
 */

/**
 * 图片封装工具类
 *
 * 使用:ImageUtils.getInstance().source().display();
 * Created by XiaoSai on 2016/11/22.
 */
public class ImageUtils{
    private DrawableRequestBuilder<?> curDrawableRequestBuilder;

    public static ImageUtils getInstance(){
        return SingleLoader.INSTANCE;
    }
    
    private static class SingleLoader{
        private static final ImageUtils INSTANCE = new ImageUtils();
    }

    /**
     * 读取网络url资源
     * @param url
     * @return
     */
    public ImageUtils source(Context context, String url){
        curDrawableRequestBuilder = getDrawableRequestBuilder(context,url);
        return this;
    }

    /**
     * 读取drawable资源
     * @param rid
     * @return
     */
    public ImageUtils source(Context context, int rid){
        curDrawableRequestBuilder = getDrawableRequestBuilder(context,rid);
        return this;
    }

    /**
     * 读取文件资源
     * @param file
     * @return
     */
    public ImageUtils source(Context context, File file){
        curDrawableRequestBuilder = getDrawableRequestBuilder(context,file);
        return this;
    }

    public ImageUtils sourceFile(Context context,String filePath){
        String fileStr = getFileSource(filePath);
        curDrawableRequestBuilder = getDrawableRequestBuilder(context,fileStr);
        return this;
    }

    /**
     * 读取raw资源
     * @param rawRid
     * @return
     */
    public ImageUtils sourceRaw(Context context, int rawRid){
        String rawStr = getRawSource(context,rawRid);
        curDrawableRequestBuilder = getDrawableRequestBuilder(context,rawStr);
        return this;
    }

    /**
     * 读取assist资源
     * @param assistPath
     * @return
     */
    public ImageUtils sourceAssist(Context context, String assistPath){
        String assistStr = getAssetsSource(assistPath);
        curDrawableRequestBuilder = getDrawableRequestBuilder(context,assistStr);
        return this;
    }

    /**
     * 获取基本的RequestBuilder
     * @param context
     * @param source
     * @return
     */
    private <T> DrawableRequestBuilder<T>  getDrawableRequestBuilder(Context context,T source){
        return Glide.with(context).load(source)
                .centerCrop()                                       //居中裁切
                .crossFade()                                        //设置加载渐变动画
                .fallback(null)                                     //设置model为空时要显示的Drawable。如果没设置fallback，model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
                .error(null)                                        //设置load失败时显示的Drawable。
                .placeholder(null);                                 //设置资源加载过程中的占位Drawable。;
    }

    /**
     * 圆形裁切
     * @return
     */
    public ImageUtils cropCircleTransformation(Context context){
        if(curDrawableRequestBuilder!=null){
            curDrawableRequestBuilder.bitmapTransform(new CropCircleTransformation(context));
        }
        return this;
    }

    /**
     * 圆角裁切
     * @return
     */
    public ImageUtils roundedCornersTransformation(Context context){
        if(curDrawableRequestBuilder!=null){
            curDrawableRequestBuilder.bitmapTransform(new RoundedCornersTransformation(context,30,0, RoundedCornersTransformation.CornerType.ALL));
        }
        return this;
    }

    /**
     * 灰度处理
     * @return
     */
    public ImageUtils grayscaleTransformation(Context context){
        if(curDrawableRequestBuilder!=null){
            curDrawableRequestBuilder.bitmapTransform(new GrayscaleTransformation(context));
        }
        return this;
    }
    
    /**
     * 显示到ImageView上
     * @param imageView
     */
    public void display(ImageView imageView){
        if(curDrawableRequestBuilder!=null){
            curDrawableRequestBuilder.into(imageView);
            curDrawableRequestBuilder = null;
        }
    }
    
    /**
     * 显示到ImageView上
     * @param imageView
     */
    public void display(final ImageView imageView,Handler handler){
        if(curDrawableRequestBuilder!=null){
            handler.post(new Runnable(){
                @Override
                public void run(){
                    curDrawableRequestBuilder.into(imageView);
                    curDrawableRequestBuilder = null;
                }
            });
        }
    }

    /**
     * 设置基本配置
     * @return
     */
    public ImageUtils baseConfig(){
        curDrawableRequestBuilder
                .centerCrop()                                       //居中裁切
                .crossFade()                                        //设置加载渐变动画
                .fallback(null)                                     //设置model为空时要显示的Drawable。如果没设置fallback，model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
                .error(null)                                        //设置load失败时显示的Drawable。
                .placeholder(null);                                 //设置资源加载过程中的占位Drawable。
        return this;
    }

    
    private String getFileSource(String fullPath){
        return "file://"+ fullPath;
    }
    private String getAssetsSource(String fileName){
        return "file:///android_asset/"+fileName;
    }
    private String getRawSource(Context context,int rawRid){
        return "android.resource://"+context.getPackageName()+"/raw/"+rawRid;
    }
    private String getDrawableSource(Context context,int drawRid){
        return "android.resource://"+context.getPackageName()+"/drawable/"+drawRid;
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
     * @param context 上下文
     * @param cacheReceiveListener 获取文件大小监听
     */
    public void getDataDiskCacheSize(Context context,DiskCacheSizeTask.CacheReceiveListener cacheReceiveListener){
        new DiskCacheSizeTask(cacheReceiveListener).execute(new File(context.getCacheDir(),DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
    }

    /**
     * 获取SD卡下缓存大小
     * @param context 上下文
     * @param cacheReceiveListener 获取文件大小监听
     */
    public void getSDDiskCacheSize(Context context,DiskCacheSizeTask.CacheReceiveListener cacheReceiveListener){
        new DiskCacheSizeTask(cacheReceiveListener).execute(new File(context.getExternalCacheDir(),DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
    }

    /**
     * 图片请求监听
     */
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
}

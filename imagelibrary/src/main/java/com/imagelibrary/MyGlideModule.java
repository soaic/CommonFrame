package com.imagelibrary;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Glide缓存配置
 * 
 * 需在AndroidManifest配置
 * 
 * Created by XiaoSai on 2016/7/19.
 */
public class MyGlideModule implements GlideModule{
    //缓存目录 /data/data/your_package_name/image_manager_disk_cache/
    private String cache_dir = DiskCache.Factory.DEFAULT_DISK_CACHE_DIR;
    //缓存大小 250M
    private int cache_size = DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        
        //缓存到data目录
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,cache_dir,cache_size));
        
        //缓存到外部磁盘SD卡
        //builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,cache_dir, cache_size));

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        
        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        //设置图片池大小
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        //glide.register(ImageFid.class,InputStream.class, new ImageFidLoader.Factory());
    }
}
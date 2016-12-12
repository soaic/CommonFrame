package com.imagelibrary.glide;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;

/**
 * 获取缓存大小异步类
 * Created by XiaoSai on 2016/11/10.
 */

public class DiskCacheSizeTask extends AsyncTask<File, Long, Long>{
    
    private final CacheReceiveListener cacheReceiveListener;

    public DiskCacheSizeTask(CacheReceiveListener cacheReceiveListener) {
        this.cacheReceiveListener = cacheReceiveListener;
    }

    @Override
    protected void onPreExecute() { }

    @Override
    protected void onProgressUpdate(Long... values) {  }

    @Override
    protected Long doInBackground(File... dirs) {
        try {
            long totalSize = 0;
            for (File dir : dirs) {
                publishProgress(totalSize);
                totalSize += calculateSize(dir);
            }
            return totalSize;
        } catch (RuntimeException ex) {
            final String message = String.format("Cannot get size of %s: %s", Arrays.toString(dirs), ex);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    cacheReceiveListener.onFail("error："+message);
                }
            });
        }
        return 0L;
    }

    @Override
    protected void onPostExecute(Long size) {
        cacheReceiveListener.onSuccess(size);
    }

    private long calculateSize(File dir) {
        if (dir == null) return 0;
        if (!dir.isDirectory()) return dir.length();
        long result = 0;
        File[] children = dir.listFiles();
        if (children != null)
            for (File child : children)
                result += calculateSize(child);
        return result;
    }

    /**
     * 缓存获取监听
     */
    public interface CacheReceiveListener{
        void onSuccess(Long sizeBytes);
        void onFail(String str);
    }
}

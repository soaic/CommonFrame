package com.imagelibrary;

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
    
    private final TextView resultView;

    public DiskCacheSizeTask(TextView resultView) {
        this.resultView = resultView;
    }

    @Override
    protected void onPreExecute() {
        resultView.setText("Calculating...");
    }

    @Override
    protected void onProgressUpdate(Long... values) { /* onPostExecute(values[values.length - 1]); */ }

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
                    resultView.setText("error");
                    Toast.makeText(resultView.getContext(),message,Toast.LENGTH_LONG).show();
                }
            });
        }
        return 0L;
    }

    @Override
    protected void onPostExecute(Long size) {
        String sizeText = android.text.format.Formatter.formatFileSize(resultView.getContext(), size);
        resultView.setText(sizeText);
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
}

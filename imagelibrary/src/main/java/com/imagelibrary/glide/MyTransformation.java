package com.imagelibrary.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 自定义图片处理
 * Created by Administrator on 2016/7/14.
 */
public class MyTransformation extends BitmapTransformation{

    public MyTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform,int outWidth, int outHeight) {
        Bitmap result = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        // 如果BitmapPool中找不到符合该条件的Bitmap，get()方法会返回null，就需要我们自己创建Bitmap了
        if (result == null) {
            // 如果想让Bitmap支持透明度，就需要使用ARGB_8888
            result = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }
        //创建最终Bitmap的Canvas.
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setAlpha(128);
        // 将原始Bitmap处理后画到最终Bitmap中
        canvas.drawBitmap(toTransform, 0, 0, paint);
        // 由于我们的图片处理替换了原始Bitmap，就return我们新的Bitmap就行。
        // Glide会自动帮我们回收原始Bitmap。
        return result;
    }

    @Override
    public String getId() {
        // 返回代表该变换的唯一Id，会作为cache key的一部分。
        // 注意：最好不要用getClass().getName()，因为容易受混淆影响。如果变换过程不影响缓存数据，可以返回空字符串。
        return "com.example.myapp.MyTransformation";
    }

}


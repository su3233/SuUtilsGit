package demo.utils.com.myapplication.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import demo.utils.com.myapplication.MyApplication;
import demo.utils.com.myapplication.R;

/**
 *
 */
@ContentView(R.layout.activity_xutils)
public class XutilsActivity extends AppCompatActivity {
    @ViewInject(R.id.tv_mine1)
    TextView tv_mine;

    @ViewInject(R.id.bt_click)
    Button bt_click;

    @ViewInject(R.id.iv_xutils_show)
    ImageView iv_xutils_pic;

    private Context context;
    private String url = "http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg";
    private ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xutils);
        x.view().inject(this);
        context = this;
        initXutilsImage();
        initView();
    }

    private void initXutilsImage() {
        //给Xutils设置图片属性
        //图片大小
//ImageView圆角半径
// 如果ImageView的大小不是定义为wrap_content, 不要crop.
//加载中默认显示图片
//加载失败后默认显示图片
//设置使用MemCache，默认true
        imageOptions = new ImageOptions.Builder()
//                .setCircular(true)
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                .setFailureDrawableId(R.mipmap.ic_launcher)//加载失败后默认显示图片
                .setUseMemCache(true)//设置使用MemCache，默认true
                .build();
    }

    private void initView() {

        xutilsShowPicBind();
//        xutilsShowPicLoadDrawable();
//        xutilsShowPicLoadFile();
//        xutilsShowPicBind();


    }

    /**
     * 当我们通过bind()或者loadDrawable()方法加载了一张图片后，
     * 它会保存到本地文件中，那当我需要这张图片时，就可以通过loadFile()方法进行查找
     */
    private void xutilsShowPicLoadFile() {
        x.image().loadFile(url, imageOptions, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                //在这里可以做图片另存为等操作
                x.image().bind(iv_xutils_pic, result.getPath(), imageOptions);//使用缓存显示是图片
                Log.i("JAVA", "file：" + result.getPath() + result.getName());
                return true; //相信本地缓存返回true
            }

            @Override
            public void onSuccess(File result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void xutilsShowPicLoadDrawable() {
        x.image().loadDrawable(url, imageOptions, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                iv_xutils_pic.setImageDrawable(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 显示图片，重点在于加载图片的4个bind方法
     */
    private void xutilsShowPicBind() {//"http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg"
//        x.image().bind(iv_xutils_pic, url);

//        x.image().bind(iv_xutils_pic, url, imageOptions);
//        x.image().bind(iv_xutils_pic, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
//        x.image().bind(iv_xutils_pic, "/sdcard/test.gif", imageOptions);
//        x.image().bind(iv_xutils_pic, "file:///sdcard/test.gif", imageOptions);
//        x.image().bind(iv_xutils_pic, "file:/sdcard/test.gif", imageOptions);

        x.image().bind(iv_xutils_pic, url, imageOptions, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

                MyApplication.printLog("onSuccess");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                MyApplication.printLog("onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                MyApplication.printLog("onCancelled");
            }

            @Override
            public void onFinished() {
                MyApplication.printLog("onFinished");
            }
        });
    }

    /**
     * 单击事件
     * type默认View.OnClickListener.class，故此处可以简化不写，@Event(R.id.bt_main)
     */
    @Event(type = View.OnClickListener.class, value = {R.id.bt_click, R.id.tv_mine1})
    private void testInjectOnclick(View v) {
        Snackbar.make(v, "短按", Snackbar.LENGTH_LONG).show();
    }

    /**
     * 长按事件
     */
    @Event(type = View.OnLongClickListener.class, value = R.id.bt_click)
    private boolean testOnLongClickListener(View v) {
        Snackbar.make(v, "长按", Snackbar.LENGTH_SHORT).show();
        return true;
    }

}

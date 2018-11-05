package demo.utils.com.myapplication.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;

import demo.utils.com.myapplication.MyApplication;
import demo.utils.com.myapplication.R;

public class MulImageLoaderActivity extends AppCompatActivity {

    private ImageView iv_uil, iv_picasso, iv_glide;
    private ImageLoader loader;
    String uri = "file:///" + "本地路径";
    String url = "https://raw.githubusercontent.com/android-cn/android-open-project-analysis" +
            "/master/tool-lib/image-cache/universal-image-loader/image/overall-design.png";
        String bigPicUrl = "https://raw.githubusercontent.com/android-cn/android-open-project-analysis" +
            "/master/tool-lib/image-cache/universal-image-loader/image/overall-design.png";
//    String bigPicUrl = "http://imgs.yunbiaowulian.com/imgserver/resource/2018/03/23/7a84d77d-6bb1-41ac-ab1e-80d9f5fe8d88.jpg";
    //http://imgs.yunbiaowulian.com/imgserver/resource/2018/03/23/7a84d77d-6bb1-41ac-ab1e-80d9f5fe8d88.jpg
    String glideUrl = "https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_image_loader);
        loader = ImageLoader.getInstance();
        initView();

    }

    private void initView() {
        iv_uil = (ImageView) this.findViewById(R.id.iv_uil);
        iv_picasso = (ImageView) this.findViewById(R.id.iv_picasso);//iv_glide
        iv_glide = (ImageView) this.findViewById(R.id.iv_glide);//

        showImageLoaderPic();

        showPicassoPic();

        showFrescoPic();

        showGlidePic();

    }

    /**
     * 一个图片加载和缓存的库
     * 优先加载，圆形裁剪
     */
    private void showGlidePic() {
//        Glide.with(this).load(bigPicUrl).into(iv_glide);
        //用原图的1/10作为缩略图
        Glide.with(this)
                .load(bigPicUrl)
                //http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png
                .thumbnail(0.1f)
                .into(iv_glide);
        //用其它图片作为缩略图
//        DrawableRequestBuilder<Integer> thumbnailRequest = Glide
//                .with(this)
//                .load(R.drawable.news);
//        Glide.with(this)
//                .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
//                .thumbnail(thumbnailRequest)
//                .into(iv_0);
    }

    /**
     * Fresso显示是图片
     * 一个用于管理图像和他们使用的内存的库
     */
    private void showFrescoPic() {
        Uri uri = Uri.parse(bigPicUrl);
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
    }

    /**
     * 一个强大的图片下载与缓存的库,可以给加载的图片设置优先级
     */
    private void showPicassoPic() {
        //从网络加载图片
        Picasso.with(this).load(bigPicUrl).placeholder(R.mipmap.ic_launcher).into(iv_picasso);
        //一般网络加载图片耗时比较长，所以会先默认显示一张替代的图片，只支持resId和Drawable本地图片。
//        Picasso.with(this).load(url+"landptf").error(R.mipmap.ic_launcher).into(iv_uil);

        //该属性会根据Image View的大小充满整个View，不考虑比例，可能造成图片的拉伸或者缩小
//        Picasso.with(this).load(url).fit().into(iv_picasso);

//按比例裁减图片，使其居中显示，充满View，会造成图片显示不全，必须与resize方法同时使用
//        Picasso.with(this).load(url).resize(320, 640).centerCrop().into(iv_picasso);

        //按比例裁减图片，图片可以完全显示，但如果图片比View小，则无法充满整个View，必须与resize方法同时使用
//        Picasso.with(this).load(url).resize(320, 640).centerInside().into(iv_picasso);

//这里面使用的测试图片的大小是1240*1563，如果resize的宽高大于图片的原始宽高，则resize不起作用，采用图片原始宽高显示。
//        Picasso.with(this).load(url).resize(1240, 1563).onlyScaleDown().into(iv_picasso);

//Picasso提供缓存的调试方法，通过如下代码可设置
//        Picasso.with(this).setIndicatorsEnabled(true);

//以(0,0)为中心顺时针旋转45度
//        Picasso.with(this).load(url).rotate(45).into(iv_picasso);

//以(64,64)为中心顺时针旋转45度
//        Picasso.with(this).load(url).rotate(45, 64, 64).into(iv_picasso);

//跳过从内存加载图片，并且图片下载之后也不在内存中进行缓存。
//        Picasso.with(this)
//                .load(url)
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                .into(iv_picasso);

//        同理该方法表示跳过从磁盘加载图片，并且图片下载之后也不在磁盘中进行缓存。
//        Picasso.with(this)
//                .load(url)
//                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                .into(iv_picasso);

//Picasso给我们提供了priority方法来管理请求的优先级
//        Picasso.with(this)
//                .load(url)
//                .priority(Picasso.Priority.HIGH)
//                .into(iv_picasso);

        //列表滚动过程中停止加载图片，停止滚动时恢复图片加载
//        Picasso.with(this).load(url).tag("landptf").into(iv_picasso);
//        Picasso.with(this).pauseTag("landptf");
//        Picasso.with(this).resumeTag("landptf");
//        Picasso.with(this).cancelTag("landptf");//Activity销毁的时候将未完成的请求取消

    }

    /**
     * 显示ImgaLoader加载图片的结果
     * 一个强大的加载，缓存，展示图片的库
     */
    private void showImageLoaderPic() {
        loader.displayImage(bigPicUrl, iv_uil, MyApplication.getDisplayOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String arg0, View arg1) {
                Log.i("info", "onLoadingStarted");
            }

            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                Log.i("info", "onLoadingFailed");
            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                Log.i("info", "onLoadingComplete");
            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {
                Log.i("info", "onLoadingCancelled");
            }
        });
    }

}

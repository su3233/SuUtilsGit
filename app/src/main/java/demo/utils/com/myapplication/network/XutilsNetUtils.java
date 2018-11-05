package demo.utils.com.myapplication.network;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * 主要分四个重要的模块：ViewUtils，HttpUtils，BitmapUtils，DbUtils
 * 大文件上传,事件注解
 * <p>
 * Created by sts on 2018/3/23.
 */

public class XutilsNetUtils {

    /**
     * GET请求
     *
     * @param url
     */
    public static void xutilsGetRequest(String url) {
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("username", "abc");
        params.addQueryStringParameter("password", "123");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 带缓存的xutils   get
     *
     * @param url
     */
    public static void xutilsCacheGetRequest(String url) {
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("username", "abc");
        params.addQueryStringParameter("password", "123");
// 默认缓存存活时间, 单位:毫秒（如果服务器没有返回有效的max-age或Expires则参考）
        params.setCacheMaxAge(1000 * 60);

        x.http().get(params, new Callback.CacheCallback<String>() {
            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) { //得到缓存数据, 缓存过期后不会进入
                this.result = result;
                return true; //true: 信任缓存数据, 不再发起网络请求; false不信任缓存数据
            }

            @Override
            public void onSuccess(String result) {
                //如果服务返回304或onCache选择了信任缓存,这时result为null
                Log.i("JAVA", "开始请求");
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                if (ex instanceof HttpException) { //网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    Log.e("ERROR_TAG", "responseCode:" + responseCode + "...responseMsg:" + responseMsg + "...errorResult:" + errorResult);
                    //...
                } else { //其他错误
                    //...
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    //成功获取数据
                    Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Post请求
     */
    public static void xutilsPostRequest(String url) {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("username", "abc");
        params.addParameter("password", "123");
        params.addHeader("head", "android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
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
     * PUT
     *
     * @param url
     */
    public static void xutilsPutRequest(String url) {
        RequestParams params = new RequestParams(url);
        params.addParameter("username", "abc");
        x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
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
     * 长传文件
     *
     * @param path 文件路径
     */
    public static void xutilsUploadRequest(String path, String url) {
//        String path = "/mnt/sdcard/Download/icon.jpg";
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file", new File(path));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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
     * 下载文件
     *
     * @param path 文件路径
     */
    public static void xutilsDownloadRequest(String path, String url) {
        url = "http://127.0.0.1/server/abc.apk";
        RequestParams params = new RequestParams(url);
//自定义保存路径，Environment.getExternalStorageDirectory()：SD卡的根目录
        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/myapp/");
//自动为文件命名
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                //apk下载完成后，调用系统的安装方法
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
//                MyApplication.genInstance().startActivity(intent);
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

            //网络请求之前回调
            @Override
            public void onWaiting() {
            }

            //网络请求开始的时候回调
            @Override
            public void onStarted() {
            }

            //下载的时候不断回调的方法
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //当前进度和文件总大小
                Log.i("JAVA", "current：" + current + "，total：" + total);
            }
        });
    }

    /**
     * 线程异步类
     */
    public static void asynTaskThread(){
        x.task().run(new Runnable() {
            @Override
            public void run() {
                //异步代码
            }
        });
    }

    /**
     * 线程同步类
     */
    public static void synTaskThread(){
        x.task().post(new Runnable() {
            @Override
            public void run() {
                //同步代码
            }
        });
    }


}

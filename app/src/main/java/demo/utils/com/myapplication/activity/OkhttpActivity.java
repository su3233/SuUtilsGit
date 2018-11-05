package demo.utils.com.myapplication.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import demo.utils.com.myapplication.Global;
import demo.utils.com.myapplication.MainActivity;
import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.entity.ResponseBean;
import demo.utils.com.myapplication.network.OkHttpUtil;
import okhttp3.Call;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    private EditText etAccount, etPassword;
    private Context context;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        context = OkhttpActivity.this;
        initView();
    }

    private void initView() {
        etAccount = (EditText) this.findViewById(R.id.tv_account);
        etPassword = (EditText) this.findViewById(R.id.tv_password);

    }

    /**
     * 异步get请求
     *
     * @param view
     */
    public void login(View view) {
        final String account = etAccount.getText().toString();
        final String pass = etPassword.getText().toString();
        if (account.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!OkHttpUtil.isNetworkAvailable(context)) {
            Toast.makeText(this, "网络不可用！", Toast.LENGTH_SHORT).show();
            return;
        }
        //进度条
        loginProgress.setVisibility(View.VISIBLE);
        //获取网络工具类实例
        OkHttpUtil OkHttpUtil = new OkHttpUtil().getInstance();
        //请求网络，一句代码搞定
        OkHttpUtil.getDataAsynFromNet(Global.LOGIN + "?username=" + account + "&password=" + pass,
                new OkHttpUtil.MyNetCall() {
                    @Override
                    public void success(Call call, Response response) throws IOException {
                        Log.i("tag", "success");
                        String result = response.body().string();
                        final ResponseBean responseBean = JSON.parseObject(result, ResponseBean.class);

                        if (responseBean != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loginProgress.setVisibility(View.GONE);
                                    String errcode = responseBean.getErrcode();
                                    if (errcode.equals("0")) {//登录成功
                                        //记录学号
                                        Global.account = account;
                                        //存储用户名密码
//                                        saveUserName(account, pass);

                                        Intent intent = new Intent(context, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(context, "请求失败！错误代码： " + errcode, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }
                    }

                    @Override
                    public void failed(Call call, IOException e) {
                        Log.i("tag", "failed");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loginProgress.setVisibility(View.GONE);
                                Toast.makeText(OkhttpActivity.this, "请求失败！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

        );

    }

    /**
     * 异步post请求
     * OkHttp封装
     */
    public void postSelect() {
        //确保选择了楼号
        if (etAccount.getText().toString().isEmpty()) {
            Toast.makeText(context, "请选择楼号！", Toast.LENGTH_SHORT).show();
            return;
        }
        //构造请求参数
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("num", "1");
        reqBody.put("stuid", Global.account);
        reqBody.put("buildingNo", "selectBuilding" + "");
        //获取网络请求工具类实例
        OkHttpUtil OkHttpUtil = new OkHttpUtil().getInstance();
        //提交数据
        OkHttpUtil.postDataAsynToNet(Global.SELECT_ROOM, reqBody, new OkHttpUtil.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                Log.i("tag", "success");
                String result = response.body().string();
                Log.i("tag", "result: " + result);
                //解析数据
                JSONObject jsonObject1 = JSON.parseObject(result);
                if (jsonObject1 != null) {
                    final int error_code = jsonObject1.getIntValue("error_code");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("tag", "errcode: " + error_code);
                            if (error_code == 0) {//提交成功
                                Toast.makeText(context, "选择成功！", Toast.LENGTH_SHORT).show();
                                //跳转到selectSuccessfragment
//                                MainActivity.mainActivityInstance.switchFragment(getParentFragment(), SelectSuccessFragment.newInstance("", ""));
                            } else {
                                Toast.makeText(context, "选择失败！错误代码： " + error_code, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void failed(Call call, IOException e) {
                Log.i("tag", "failed");
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "请求失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}

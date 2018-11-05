package demo.utils.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import demo.utils.com.myapplication.R;

/**
 * 进度条
 */
public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private ProgressDialog mProgressDialog;//
    private ProgressBar bar,pb_circle;
    private TextView textView;
    private SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        mProgressDialog = new ProgressDialog(this);
        initView();
    }

    private void initView() {
        progressBar = (ProgressBar) this.findViewById(R.id.pb);//pbNormal
        pb_circle = (ProgressBar) this.findViewById(R.id.pbNormal);//
        bar = (ProgressBar) findViewById(R.id.pb_progress_self_define);
        textView = (TextView) findViewById(R.id.tvProgress);
        seekBar = (SeekBar)findViewById(R.id.seekBar_id);

        progressBar.setProgress(46);
        this.findViewById(R.id.bt_progressDialog).setOnClickListener(this);
        this.findViewById(R.id.bt_progress_bar_define).setOnClickListener(this);
        this.findViewById(R.id.bt_show).setOnClickListener(this);
        this.findViewById(R.id.bt_dismiss).setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int progress = 0;
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                System.out.println(progress);
                Toast.makeText(getApplicationContext(),"当前进度 = "+progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int p, boolean arg2) {
                progress = p;
            }
        });
//        progressBar.set
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_progressDialog:
                showProgressDialog();
                break;

            case R.id.bt_progress_bar_define:
                showDefineProgressBar();
                break;

            case R.id.bt_show:
                pb_circle.setVisibility(View.VISIBLE);
                break;

            case R.id.bt_dismiss:
                pb_circle.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 自定义ProgressBar
     */
    private void showDefineProgressBar() {
        new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    i++;
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int j = i;
                    bar.setProgress(i);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(j + "%");
                        }
                    });
                }
            }
        }.start();
    }

    private void showProgressDialog() {
        // 设置水平进度条
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setIcon(R.mipmap.ic_launcher);
        mProgressDialog.setTitle("提示");

        mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(ProgressBarActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });

        mProgressDialog.setMessage("这是一个水平进度条");
        mProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100 && mProgressDialog.getProgress() != 100) {
                    try {
                        Thread.sleep(200);
                        // 更新进度条的进度,可以在子线程中更新进度条进度
                        mProgressDialog.incrementProgressBy(1);
                        i++;
                    } catch (Exception e) {
                    }
                }
                // 在进度条走完时删除Dialog
                mProgressDialog.dismiss();
            }
        }).start();
    }
}

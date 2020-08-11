package demo.utils.com.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.utils.AppManager;

/**
 * 组件 控件
 */
@ContentView(R.layout.activity_commponent)
public class CommponentActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.tv_check_box)
    TextView tv_checkBox;

    @ViewInject(R.id.tv_progress_bar)
    TextView tv_progressBar;

    @ViewInject(R.id.tv_tableLayout)
    TextView tv_tableLayout;

    @ViewInject(R.id.tv_radioButton)
    TextView radioButton;

    @ViewInject(R.id.tv_gridLayout)
    TextView gridLayout;

    @ViewInject(R.id.tv_analogClock)
    TextView analogClock;

    @ViewInject(R.id.tv_toggleButton)
    TextView toggleButton;

    @ViewInject(R.id.tv_datePickern)
    TextView datePicker;

    @ViewInject(R.id.tv_charts)
    TextView tv_charts;

    @ViewInject(R.id.tv_test_global_dialog)
    TextView tv_global_dialog;

    @ViewInject(R.id.tv_test_stick_item)
    TextView tv_stick_item;

    private Context context;
    private AppManager appManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commponent);

        x.view().inject(this);
        appManager = new AppManager().getAppManager();
        initView();

        context = this;
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void initView() {
        tv_progressBar.setOnClickListener(this);
        tv_checkBox.setOnClickListener(this);
        tv_tableLayout.setOnClickListener(this);
        radioButton.setOnClickListener(this);
        gridLayout.setOnClickListener(this);
        analogClock.setOnClickListener(this);
        datePicker.setOnClickListener(this);
        tv_charts.setOnClickListener(this);
        toggleButton.setOnClickListener(this);
        tv_global_dialog.setOnClickListener(this);
        tv_stick_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_progress_bar:
                startActivity(new Intent(context, ProgressBarActivity.class));
                break;

            case R.id.tv_check_box:
                startActivity(new Intent(context, CheckBoxActivity.class));
                break;

            case R.id.tv_gridLayout:
                startActivity(new Intent(context, GridLayoutActivity.class));
                break;

            case R.id.tv_radioButton:
                startActivity(new Intent(context, RadioButtonActivity.class));
                break;

            case R.id.tv_analogClock:
                startActivity(new Intent(context, AnalogClockActivity.class));
                break;

            case R.id.tv_datePickern:
                startActivity(new Intent(context, DatePickerActivity.class));
                break;

            case R.id.tv_tableLayout:
//                MyApplication.genInstance().startActivityManager(context,TableLayoutActivity.class);
                startActivity(new Intent(context, TableLayoutActivity.class));
                break;

            case R.id.tv_charts:
                startActivity(new Intent(context, ChartsActivity.class));
                break;

            case R.id.tv_toggleButton:
                startActivity(new Intent(context, ToggleButtonActivity.class));
                break;

            case R.id.tv_test_global_dialog:
//                startActivity(new Intent(context, GlobalDialogActivity.class));
                GlobalDialogActivity.start(this.getApplicationContext());
                break;

            case R.id.tv_test_stick_item:
                startActivity(new Intent(context, StickItemActivity.class));
                break;
        }

    }
}

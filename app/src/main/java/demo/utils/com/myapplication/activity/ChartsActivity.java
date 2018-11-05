package demo.utils.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import demo.utils.com.myapplication.R;

@ContentView(R.layout.activity_charts)
public class ChartsActivity extends AppCompatActivity implements View.OnClickListener {


    @ViewInject(R.id.tv_williamchart)
    TextView williamchart;

    @ViewInject(R.id.tv_HelloCharts)
    TextView HelloCharts;

    @ViewInject(R.id.tv_MPAndroidChart)
    TextView MPAndroidChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_charts);
        x.view().inject(this);
        initView();

    }

    private void initView() {
        HelloCharts.setOnClickListener(this);
        williamchart.setOnClickListener(this);
        MPAndroidChart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_williamchart:
                startActivity(new Intent(ChartsActivity.this, WilliamChartActivity.class));
                break;

            case R.id.tv_HelloCharts:
                startActivity(new Intent(ChartsActivity.this, HelloChartsActivity.class));
                break;

            case R.id.tv_MPAndroidChart:
                startActivity(new Intent(ChartsActivity.this, MPAndroidChartActivity.class));
                break;
        }

    }
}

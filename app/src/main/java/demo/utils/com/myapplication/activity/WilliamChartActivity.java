package demo.utils.com.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.renderer.XRenderer;
import com.db.chart.util.Tools;
import com.db.chart.view.StackBarChartView;

import java.util.Arrays;

import demo.utils.com.myapplication.R;

/**
 * 折线图，条形图，横向条形图，堆叠柱形图，横向堆叠柱形图
 */
public class WilliamChartActivity extends AppCompatActivity {
    float[] topChartData = {26, 2, 4, 2, 10, 20, 20, 18, 10, 50, 32, 2, 4, 2, 10, 20, 20, 18, 21, 5};
    float[] bottomChartData = new float[topChartData.length];
    private StackBarChartView roundBarChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_william_chart);
        Arrays.fill(bottomChartData, 0.1f);
        initView();
    }

    private void initView() {
        BarSet topBarSet = new BarSet();
        int i;
        for (i = 0; i < topChartData.length; i++) {
            Bar bar = new Bar(i + "", topChartData[i]);
            bar.setColor(Color.parseColor("#47b484"));
            topBarSet.addBar(bar);
        }

        BarSet bottomBarSet = new BarSet();
        for (i = 0; i < bottomChartData.length; i++) {
            Bar bar = new Bar(i + "", bottomChartData[i]);
            bar.setColor(Color.parseColor("#47b484"));
            bottomBarSet.addBar(bar);
        }

        roundBarChart = (StackBarChartView) findViewById(R.id.round_barchart);
        roundBarChart.addData(bottomBarSet); //先添加底部数据
        roundBarChart.addData(topBarSet);

        roundBarChart.setBarSpacing(Tools.fromDpToPx(10.0f));  //设置柱子的间隔
        roundBarChart.setRoundCorners(Tools.fromDpToPx(2.0f)); //设置圆角的角度
        // 去除X,Y轴
        roundBarChart.setXAxis(true)
                .setYAxis(true)
//                .setXLabels(XRenderer.LabelPosition.NONE)
                .setXLabels(XRenderer.LabelPosition.OUTSIDE)
//                .setYLabels(XRenderer.LabelPosition.NONE);
                .setYLabels(XRenderer.LabelPosition.OUTSIDE);
        // 图表展示
        roundBarChart.show();

    }
}

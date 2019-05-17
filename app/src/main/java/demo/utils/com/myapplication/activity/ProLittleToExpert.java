package demo.utils.com.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.activity.little_to_expert.LittleTabViewpagerActivity;

/**
 * 从小工到专家
 */
public class ProLittleToExpert extends AppCompatActivity {

    @BindView(R.id.tv_tab_viewpager)
    TextView tvTabViewpager;
    @BindView(R.id.tv_my_view)
    TextView tvMyView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_little_to_expert);
        ButterKnife.bind(this);
        mContext = ProLittleToExpert.this;
    }

    @OnClick({R.id.tv_tab_viewpager, R.id.tv_my_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_viewpager://tab+viewPager
                startActivity(new Intent(mContext, LittleTabViewpagerActivity.class));
                break;
            case R.id.tv_my_view:
                break;
        }
    }
}

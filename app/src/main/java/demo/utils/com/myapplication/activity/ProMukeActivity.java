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

/**
 * 慕课网学习
 */
public class ProMukeActivity extends AppCompatActivity {

    @BindView(R.id.tv_pro_greendao)
    TextView tvProGreendao;
    @BindView(R.id.tv_qian_service)
    TextView tvProOther;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_muke);
        mContext = ProMukeActivity.this;
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_pro_greendao, R.id.tv_qian_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pro_greendao:

                break;
            case R.id.tv_qian_service:
//                startActivity(new Intent(this,));
                break;

//            case R.id.tv_tab_viewpager:
//                startActivity(new Intent(mContext,));
//                break;
        }
    }
}

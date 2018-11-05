package demo.utils.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.utils.com.myapplication.R;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_alpha_title)
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

    }

    private void initView() {
//        this.findViewById(R.id.tv_alpha_title).setOnClickListener(this);
        this.findViewById(R.id.tv_alpha_splash).setOnClickListener(this);
        this.findViewById(R.id.tv_database_people).setOnClickListener(this);//tv_roll_gift
        this.findViewById(R.id.tv_roll_gift).setOnClickListener(this);//
        this.findViewById(R.id.tv_baidumap).setOnClickListener(this);
        this.findViewById(R.id.tv_surfaceview).setOnClickListener(this);
        this.findViewById(R.id.tv_image_loader).setOnClickListener(this);
        this.findViewById(R.id.tv_my_db).setOnClickListener(this);
        this.findViewById(R.id.tv_xutils).setOnClickListener(this);
        tv_title.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alpha_title:
                startActivity(new Intent(this, AlphaTitleBarActivity.class));
                break;

            case R.id.tv_alpha_splash://tv_database_people
                startActivity(new Intent(this, AlpaSplashActivity.class));
                break;

            case R.id.tv_database_people:
                startActivity(new Intent(this, DataBaseActivity.class));
                break;

            case R.id.tv_roll_gift:
                startActivity(new Intent(this, RollToGetGift.class));
                break;

            case R.id.tv_baidumap:
                startActivity(new Intent(this, BaiduMapActivity.class));
                break;

            case R.id.tv_surfaceview:
                startActivity(new Intent(this, SurfaceViewActivity.class));
                break;

            case R.id.tv_image_loader:
                startActivity(new Intent(this, MulImageLoaderActivity.class));
                break;

            case R.id.tv_my_db:
                startActivity(new Intent(this, MyDbActivity.class));
                break;

            case R.id.tv_xutils://
                startActivity(new Intent(this, XutilsActivity.class));
                break;
        }
    }
}

package demo.utils.com.myapplication.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.utils.MyScrollView;

/**
 * 可滑动的标题栏
 */
public class AlphaTitleBarActivity extends AppCompatActivity {
    private MyScrollView scrollView;
    private TextView tv;
    private TextView tv_on_move;
    private View view;
    private TextView tv_on_move2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_title_bar);
        initView();
    }

    private void initView() {
        scrollView = (MyScrollView) findViewById(R.id.msv);
        tv = (TextView) findViewById(R.id.tv_title);
        tv_on_move = (TextView) findViewById(R.id.tv_on_remove);
        tv_on_move2 = (TextView) findViewById(R.id.tv_no_remove2);
        view = findViewById(R.id.view);
        scrollView.setOnMyScrollViewScrollListener(new MyScrollView.onMyScrollViewScrollListener() {

            private int measuredHeight;

            @SuppressLint("NewApi")
            @Override
            public void onScroll(float scrollY) {
                //   Log.i("scroll1", scrollY+"");
                int scale = (int) (scrollY / 300 * 255);
                if (scale > 255) {
                    scale = 255;
                }
                if (scale < 0) {
                    scale = 0;
                }
                tv.setBackgroundColor(Color.argb(scale, 0, 255, 0));
                int[] location1 = new int[2];
                view.getLocationOnScreen(location1);
                int[] location = new int[2];
                tv_on_move.getLocationOnScreen(location);
                if (location[1] < location1[1]) {
                    tv_on_move.setVisibility(View.GONE);
                    tv_on_move2.setVisibility(View.VISIBLE);
                } else {
                    tv_on_move.setVisibility(View.VISIBLE);
                    tv_on_move2.setVisibility(View.GONE);
                }
                Log.i("scroll2", location[1] + ":" + location1[1]);
            }
        });
    }


}

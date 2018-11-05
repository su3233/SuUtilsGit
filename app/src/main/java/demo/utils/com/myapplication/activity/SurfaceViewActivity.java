package demo.utils.com.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.ui.MySurfaceView;

public class SurfaceViewActivity extends AppCompatActivity implements View.OnClickListener {

    private MySurfaceView mysurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);

        initView();
    }

    private void initView() {
        mysurface = (MySurfaceView) this.findViewById(R.id.my_sv);
        this.findViewById(R.id.bt_clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_clear:
                mysurface.reDraw();
                break;
        }
    }
}

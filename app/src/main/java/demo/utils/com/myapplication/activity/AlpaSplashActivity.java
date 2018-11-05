package demo.utils.com.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import demo.utils.com.myapplication.R;

/**
 * 透明度闪屏-常作为启动页
 */
public class AlpaSplashActivity extends AppCompatActivity {
    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpa_splash);
        splashImage = (ImageView) findViewById(R.id.iv_splan);
        setAlphaAnimation();
    }

    private void setAlphaAnimation() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(3000);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Intent intent = new Intent(AlpaSplashActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashImage.setAnimation(alpha);
    }


}

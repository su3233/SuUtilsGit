package demo.utils.com.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import demo.utils.com.myapplication.activity.PromoteActivity;
import demo.utils.com.myapplication.activity.CommponentActivity;
import demo.utils.com.myapplication.activity.FrameActivity;
import demo.utils.com.myapplication.activity.MoreActivity;
import demo.utils.com.myapplication.activity.DemoActivity;

/**
 * 主activity
 */
public class MainActivity extends TabActivity implements View.OnClickListener {

    public static String TAB_TAG_HOME = "home";
    public static String TAB_TAG_CHANNEL = "channel";
    public static String TAB_TAG_ACCOUNT = "account";
    public static String TAB_TAG_SEARCH = "search";
    public static String TAB_TAB_MORE = "more";
    public static TabHost mTabHost;
    static final int COLOR1 = Color.parseColor("#787878");
    static final int COLOR2 = Color.parseColor("#ffffff");
    ImageView mBut1, mBut2, mBut3, mBut4, mBut5;
    TextView mCateText1, mCateText2, mCateText3, mCateText4, mCateText5;
    Intent mFrameItent, mCommponentIntent, mDemoIntent, mAccountIntent,
            mMoreIntent;
    int mCurTabId = R.id.channel1;
    // Animation
    private Animation left_in, left_out;
    private Animation right_in, right_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        prepareAnim();
        prepareIntent();
        setupIntent();
        prepareView();
    }

    @Override
    public void onClick(View view) {
        if (mCurTabId == view.getId()) {
            return;
        }
        mBut1.setImageResource(R.mipmap.icon_1_n);
        mBut2.setImageResource(R.mipmap.icon_2_n);
        mBut3.setImageResource(R.mipmap.icon_3_n);
        mBut4.setImageResource(R.mipmap.icon_4_n);
        mBut5.setImageResource(R.mipmap.icon_5_n);
        mCateText1.setTextColor(COLOR1);
        mCateText2.setTextColor(COLOR1);
        mCateText3.setTextColor(COLOR1);
        mCateText4.setTextColor(COLOR1);
        mCateText5.setTextColor(COLOR1);
        int checkedId = view.getId();
        final boolean o;
        if (mCurTabId < checkedId)
            o = true;
        else
            o = false;
        if (o)
            mTabHost.getCurrentView().startAnimation(left_out);
        else
            mTabHost.getCurrentView().startAnimation(right_out);
        switch (checkedId) {
            case R.id.channel1:
                mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
                mBut1.setImageResource(R.mipmap.icon_1_c);
                mCateText1.setTextColor(COLOR2);
                break;

            case R.id.channel2:
                mTabHost.setCurrentTabByTag(TAB_TAG_CHANNEL);
                mBut2.setImageResource(R.mipmap.icon_2_c);
                mCateText2.setTextColor(COLOR2);
                break;

            case R.id.channel3:
                mTabHost.setCurrentTabByTag(TAB_TAG_SEARCH);
                mBut3.setImageResource(R.mipmap.icon_3_c);
                mCateText3.setTextColor(COLOR2);
                break;

            case R.id.channel4:
                mTabHost.setCurrentTabByTag(TAB_TAG_ACCOUNT);
                mBut4.setImageResource(R.mipmap.icon_4_c);
                mCateText4.setTextColor(COLOR2);
                break;

            case R.id.channel5:
                mTabHost.setCurrentTabByTag(TAB_TAB_MORE);
                mBut5.setImageResource(R.mipmap.icon_5_c);
                mCateText5.setTextColor(COLOR2);
                break;
            default:
                break;
        }

        if (o)
            mTabHost.getCurrentView().startAnimation(left_in);
        else
            mTabHost.getCurrentView().startAnimation(right_in);
        mCurTabId = checkedId;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mBut1.performClick();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setupIntent() {
        mTabHost = getTabHost();
        mTabHost.addTab(buildTabSpec(TAB_TAG_HOME, R.string.category_home,
                R.mipmap.icon_1_n, mFrameItent));
        mTabHost.addTab(buildTabSpec(TAB_TAG_CHANNEL,
                R.string.category_channel, R.mipmap.icon_2_n, mCommponentIntent));
        mTabHost.addTab(buildTabSpec(TAB_TAG_SEARCH, R.string.category_search,
                R.mipmap.icon_3_n, mDemoIntent));
        mTabHost.addTab(buildTabSpec(TAB_TAG_ACCOUNT,
                R.string.category_account, R.mipmap.icon_4_n, mAccountIntent));
        mTabHost.addTab(buildTabSpec(TAB_TAB_MORE, R.string.category_more,
                R.mipmap.icon_5_n, mMoreIntent));
    }

    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
                                         final Intent content) {
        return mTabHost
                .newTabSpec(tag)
                .setIndicator(getString(resLabel),
                        getResources().getDrawable(resIcon))
                .setContent(content);
    }


    private void prepareView() {
        mBut1 = (ImageView) findViewById(R.id.imageView1);
        mBut2 = (ImageView) findViewById(R.id.imageView2);
        mBut3 = (ImageView) findViewById(R.id.imageView3);
        mBut4 = (ImageView) findViewById(R.id.imageView4);
        mBut5 = (ImageView) findViewById(R.id.imageView5);
        findViewById(R.id.channel1).setOnClickListener(this);
        findViewById(R.id.channel2).setOnClickListener(this);
        findViewById(R.id.channel3).setOnClickListener(this);
        findViewById(R.id.channel4).setOnClickListener(this);
        findViewById(R.id.channel5).setOnClickListener(this);
        mCateText1 = (TextView) findViewById(R.id.textView1);
        mCateText2 = (TextView) findViewById(R.id.textView2);
        mCateText3 = (TextView) findViewById(R.id.textView3);
        mCateText4 = (TextView) findViewById(R.id.textView4);
        mCateText5 = (TextView) findViewById(R.id.textView5);
    }

    private void prepareIntent() {
        mFrameItent = new Intent(this, FrameActivity.class);
        mCommponentIntent = new Intent(this, CommponentActivity.class);
        mDemoIntent = new Intent(this, DemoActivity.class);
        mAccountIntent = new Intent(this, PromoteActivity.class);//PromoteActivity
        mMoreIntent = new Intent(this, MoreActivity.class);
    }


    private void prepareAnim() {
        left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
        left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);

        right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
        right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
    }
}

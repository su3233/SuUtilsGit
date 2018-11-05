package demo.utils.com.myapplication.utils;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by sts on 2017/12/14.
 */

public class MyScrollView extends ScrollView {
    protected static final int ACTION_UP = 0;
    private onMyScrollViewScrollListener listener;

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyScrollView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setOnMyScrollViewScrollListener(onMyScrollViewScrollListener listener) {
        this.listener = listener;
    }

    float downY = 0;
    Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==ACTION_UP){
                if(downY!=getScrollY()){
                    downY=getScrollY();
                    listener.onScroll(downY);
                    handler.sendEmptyMessageDelayed(ACTION_UP, 5);
                }else{
                    handler.removeCallbacksAndMessages(null);
                    downY=0;
                }
            }
        }
    };
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (listener != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // downY=getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    downY = this.getScrollY();
                    listener.onScroll(downY);
                    break;
                case MotionEvent.ACTION_UP:
                    downY = this.getScrollY();
                    handler.sendEmptyMessageDelayed(ACTION_UP, 5);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    handler.sendEmptyMessageDelayed(ACTION_UP, 5);
                    break;

                default:
                    break;
            }
        }

        return super.onTouchEvent(ev);
    }

    public interface onMyScrollViewScrollListener {
        public void onScroll(float scrollY);

    }
}

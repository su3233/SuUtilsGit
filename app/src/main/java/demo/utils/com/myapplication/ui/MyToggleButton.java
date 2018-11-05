package demo.utils.com.myapplication.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sts on 2018/4/4.
 */

public class MyToggleButton extends View {
    //滑动開背景图片
    private Bitmap toggle_bkg_on;
    //滑动關背景图片
    private  Bitmap toggle_bkg_off;
    //滑动块的图片
    private  Bitmap toggle_slip;
    //ToggleButton的状态
    private  Boolean toggleState=true;
    //滑动状态，false为未滑动状态
    private Boolean isSliding = false;
    //手指滑动的距离
    private float currentX;
    //声明OnToggleStateChangeListener接口变量
    private OnToggleStateChangeListener listener;

    public void setToggle_bkg_on(int toggle_bkg_on) {
        this.toggle_bkg_on = BitmapFactory.decodeResource(getResources(), toggle_bkg_on);
    }


    public void setToggle_bkg_off(int toggle_bkg_off) {
        this.toggle_bkg_off = BitmapFactory.decodeResource(getResources(), toggle_bkg_off);
    }


    public void setToggle_slip(int toggle_slip) {
        this.toggle_slip = BitmapFactory.decodeResource(getResources(), toggle_slip);
    }
    public void setToggleState(Boolean toggleState) {
        this.toggleState = toggleState;
    }


    /*帶3個參數構造方法，在xml中應用是可以指定樣式和屬性
     * context:上下文
     *AttributeSet：属性值集合
     *defStyleAttr：Theme下默认的Style
     */
    public MyToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /*
     * 帶2個參數構造方法,在xml中使用時可以指定屬性
     */
    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*
     * 帶1個參數構造方法
     */
    public MyToggleButton(Context context) {
        super(context);
    }

    /*
     * 繼承View的方法，測量髖高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }
    /*
     * 根据拖动位置绘制图像
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果移动距离小于背景一般，绘制关闭按钮背景，否则绘制打开按钮背景
        if (currentX <toggle_bkg_off.getWidth()/2){
            canvas.drawBitmap(toggle_bkg_off, 0, 0, null);
        }else{
            canvas.drawBitmap(toggle_bkg_on, 0, 0, null);
        }
        float slip_left = 0;
        if(isSliding){
            if(currentX > toggle_bkg_off.getWidth()-toggle_slip.getWidth()){
                slip_left = toggle_bkg_off.getWidth()-toggle_slip.getWidth();
                canvas.drawBitmap(toggle_slip, slip_left, 0,null);
            }else{
                slip_left = currentX - toggle_slip.getWidth()/2;
                canvas.drawBitmap(toggle_slip, slip_left, 0,null);
            }
        }else{
            if(toggleState){
                slip_left =toggle_bkg_off.getWidth() - toggle_slip.getWidth();
                canvas.drawBitmap(toggle_slip, slip_left, 0,null);
            }else{
                canvas.drawBitmap(toggle_slip, 0, 0,null);
            }
        }
        //滑出左边界
        if(currentX < 0){
            canvas.drawBitmap(toggle_slip, 0, 0,null);
        }
        //滑出右边界
        if (currentX > toggle_bkg_off.getWidth()-toggle_slip.getWidth()){
            slip_left = toggle_bkg_off.getWidth()-toggle_slip.getWidth();
            canvas.drawBitmap(toggle_slip, slip_left, 0,null);
        }


    }
    /*
     * 继承view触摸事件方法并实现逻辑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取滑动距离
        currentX = (int) event.getX();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //滑动状态改成true
                currentX = event.getX();
                isSliding=true;
                break;
            case MotionEvent.ACTION_MOVE://移动

                currentX = event.getX();

                break;
            case MotionEvent.ACTION_UP:
                //滑动状态改成false
                isSliding=false;
                //如果滑动距离大于背景的1/2将开关打开，小于1/2将开关关闭
                if(currentX < toggle_bkg_off.getWidth()/2){
                    //检测原来的开关状态是否为开，如果是开则关闭它
                    if(toggleState){
                        //关闭ToggleButton
                        toggleState = false;
                        //检测是否注册监听器，如果没有则注册
                        if(listener != null){
                            listener.onToggleStateChange(toggleState);
                        }
                    }
                }else{
                    if(!toggleState){
                        toggleState = true;
                        //检测是否注册监听器，如果没有则注册
                        if(listener !=null){
                            listener.onToggleStateChange(toggleState);
                        }
                    }
                }
                break;
        }
        //在onTouchEvent事件完成后重新调用onDraw绘制图形
        invalidate();
        //返回true表示操作完毕
        return true;
    }
    /**
     * 定义一个ToggleButton状态改变监听器接口
     */
    public interface OnToggleStateChangeListener{
        void onToggleStateChange(Boolean state);
    }
    /*
    *实现设置状态改变监听方法
    */
    public void setOnToggleStateChangeListener(OnToggleStateChangeListener listener){
        this.listener=listener;
    }
}

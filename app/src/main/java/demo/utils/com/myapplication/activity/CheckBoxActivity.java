package demo.utils.com.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import demo.utils.com.myapplication.R;

public class CheckBoxActivity extends AppCompatActivity {

    private TextView textView1,textView2;
    private CheckBox checkbox1;
    private CheckBox checkbox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        initView();
    }

    private void initView() {
        //通过控件的ID来得到代表控件的对象
        textView1 = (TextView) findViewById(R.id.text_view1);
        textView2 = (TextView) findViewById(R.id.text_view2);
        checkbox1 = (CheckBox) this.findViewById(R.id.cb1);
        checkbox2 = (CheckBox) findViewById(R.id.cb2);

        //为第一个 CheckBox 注册监听
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //如果第一个 CheckBox 被选中
                if(isChecked == true){
                    textView1.setText("CheckBox选中北京");
                }else {
                    textView1.setText("CheckBox取消选中北京");
                }
            }
        });

        //为第二个 CheckBox 注册监听
        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //如果第二个 CheckBox 被选中
                if(isChecked == true){
                    textView2.setText("CheckBox选中上海");
                }else {
                    textView2.setText("CheckBox取消选中上海");
                }
            }
        });
    }
}


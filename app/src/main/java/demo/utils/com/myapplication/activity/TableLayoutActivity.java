package demo.utils.com.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.utils.com.myapplication.R;

public class TableLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

//        android:layout_column="1"    该控件显示在第1列
//        android:layout_span="2"        该控件占据2列
//        说明：一个控件也可以同时具备这两个特性。

    }
}

package demo.utils.com.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.adapter.PromoteAllAdapter;
import demo.utils.com.myapplication.ui.MyGridView;

/**
 * 进阶
 */
public class PromoteActivity extends AppCompatActivity {

    private MyGridView gv_promote;
    private String[] itemsArr = {"慕课", "开发进阶", "设计模式", "爱上Android", "开发进阶"};
    private List<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
    }


    private void initView() {
        gv_promote = (MyGridView) findViewById(R.id.gv_promote_all);
        List<String> itemList = Arrays.asList(itemsArr);//此处转换的list不能add和remove操作，只是预览视图
        PromoteAllAdapter promoteAllAdapter = new PromoteAllAdapter(PromoteActivity.this, itemList);
        gv_promote.setAdapter(promoteAllAdapter);
    }

}

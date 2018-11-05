package demo.utils.com.myapplication.activity;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.utils.com.myapplication.R;
import demo.utils.com.myapplication.adapter.MyAdapter;
import demo.utils.com.myapplication.db.DbbaseHelper;
import demo.utils.com.myapplication.entity.People;

/**
 * 展示数据库操作
 */
public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener {

    private DbbaseHelper dbHelper;
    private SQLiteDatabase db;
    private EditText edt_Name;
    private EditText edt_Age;
    private EditText edt_Height;
    private EditText edt_Id;
    private Button bt_AddData;
    private Button bt_ShowAllData;
    private Button bt_ClearShowData;
    private Button bt_DeleteAllData;
    private Button bt_DeleteById;
    private Button bt_QueryById;
    private Button bt_UpdateById;
    private ListView listview_show;
    private String strName = "";
    private String strAge = "";
    private String strHeight = "";
    private int m_Id;
    private List<People> PeopleList = new ArrayList<People>();
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_main_view);
        dbHelper = new DbbaseHelper(this, "people.db", null, 1);
        db = dbHelper.getWritableDatabase();
        // 初始化控件
        initShow();



    }

    // 初始化控件
    private void initShow() {
        edt_Name = (EditText) findViewById(R.id.edt_name);
        edt_Age = (EditText) findViewById(R.id.edt_age);
        edt_Height = (EditText) findViewById(R.id.edt_height);
        edt_Id = (EditText) findViewById(R.id.edt_id);
        bt_AddData = (Button) findViewById(R.id.bt_adddata);
        bt_ShowAllData = (Button) findViewById(R.id.bt_showalldata);
        bt_ClearShowData = (Button) findViewById(R.id.bt_clearshowdata);
        bt_DeleteAllData = (Button) findViewById(R.id.bt_deletealldata);
        bt_DeleteById = (Button) findViewById(R.id.bt_deteleid);
        bt_QueryById = (Button) findViewById(R.id.bt_queryid);
        bt_UpdateById = (Button) findViewById(R.id.bt_updateid);
        listview_show = (ListView) findViewById(R.id.listview_show);

        // 添加数据
        bt_AddData.setOnClickListener(this);
        // 全部显示
        bt_ShowAllData.setOnClickListener(this);
        // 清楚显示
        bt_ClearShowData.setOnClickListener(this);
        // 全部删除
        bt_DeleteAllData.setOnClickListener(this);
        // ID删除
        bt_DeleteById.setOnClickListener(this);
        // ID查询
        bt_QueryById.setOnClickListener(this);
        // ID更新
        bt_UpdateById.setOnClickListener(this);
    }

    // listview显示数据库数据
    private void showData() {
        mAdapter = new MyAdapter(DataBaseActivity.this, PeopleList);
        listview_show.setAdapter(mAdapter);

    }

    // 获取edittext中输入的ID
    public int getEd_ID() {
        if (!edt_Id.getText().toString().equals("")) {
            String strId = edt_Id.getText().toString();
            m_Id = Integer.parseInt(strId);
        } else {
            m_Id = -1;
        }
        return m_Id;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 添加数据
            case R.id.bt_adddata:
                addData();
                edt_Name.setText("");
                edt_Age.setText("");
                edt_Height.setText("");
                break;
            // 全部显示
            case R.id.bt_showalldata:
                showAllData();
                showData();
                break;
            // 清楚显示
            case R.id.bt_clearshowdata:
                clearListView();
                showData();
                break;
            // 全部删除
            case R.id.bt_deletealldata:
                deleteData();
                showData();
                break;
            // ID删除
            case R.id.bt_deteleid:
                m_Id = getEd_ID();
                deleteById(m_Id);
                showAllData();
                showData();
                edt_Id.setText("");
                break;
            // ID查询
            case R.id.bt_queryid:
                m_Id = getEd_ID();
                queryById(m_Id);
                showData();
                edt_Id.setText("");
                break;
            // ID更新
            case R.id.bt_updateid:
                m_Id = getEd_ID();
                updateById(m_Id);
                showData();
                edt_Id.setText("");
                break;

        }

    }

    // ID更新
    private void updateById(int mId) {
        if (mId == -1) {
            Toast.makeText(DataBaseActivity.this, "请输入ID号", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // 先找到这条数据，回显
            db = dbHelper.getReadableDatabase();
            final People people = new People();
            List<People> tempList = new ArrayList<People>();
            Cursor cursor = db.query("peopleinfo", null, "_id" + "=" + mId, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int age = cursor.getInt(cursor.getColumnIndex("age"));
                    float height = cursor.getFloat(cursor.getColumnIndex("height"));
                    people.setID(id);
                    people.setName(name);
                    people.setAge(age);
                    people.setHeight(height);
                    tempList.add(people);
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (tempList.size() == 0) {
                Toast.makeText(DataBaseActivity.this, "数据库中没有这条信息",
                        Toast.LENGTH_SHORT).show();
            } else {
                // 弹出自定义的AlertDialog
                LayoutInflater factory = LayoutInflater.from(this);
                final View textChangeView = factory.inflate(R.layout.custom,
                        null);
                final EditText editTextName = (EditText) textChangeView
                        .findViewById(R.id.cEdt_name);
                final EditText editTextAge = (EditText) textChangeView
                        .findViewById(R.id.cEdt_age);
                final EditText editTextHeight = (EditText) textChangeView
                        .findViewById(R.id.cEdt_height);

                editTextName.setText(people.getName());
                editTextAge.setText(Integer.toString(people.getAge()));
                editTextHeight.setText(Float.toString(people.getHeight()));

                AlertDialog.Builder ad = new AlertDialog.Builder(
                        DataBaseActivity.this);
                ad.setTitle("ID更新：");
                ad.setIcon(android.R.drawable.ic_dialog_info);
                ad.setView(textChangeView);
                ad.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                ContentValues updateValues = new ContentValues();
                                strName = editTextName.getText().toString();
                                strAge = editTextAge.getText().toString();
                                strHeight = editTextHeight.getText().toString();
                                int iAge = Integer.parseInt(strAge);
                                float fHeight = Float.parseFloat(strHeight);
                                // 开始组装一条数据
                                updateValues.put("name", strName);
                                updateValues.put("age", iAge);
                                updateValues.put("height", fHeight);
                                db.update("peopleinfo", updateValues, "_id"
                                        + "=" + people.getID(), null);
                                Toast.makeText(DataBaseActivity.this, "更新成功",
                                        Toast.LENGTH_SHORT).show();

                                Cursor cursor = db.query("peopleinfo", null, "_id" + "=" + people.getID(),
                                        null, null, null, null);
                                PeopleList.clear();
                                if (cursor.moveToFirst()) {
                                    do {
                                        int id = cursor.getInt(cursor.getColumnIndex("_id"));
                                        String name = cursor.getString(cursor.getColumnIndex("name"));
                                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                                        float height = cursor.getFloat(cursor.getColumnIndex("height"));
                                        people.setID(id);
                                        people.setName(name);
                                        people.setAge(age);
                                        people.setHeight(height);
                                        PeopleList.add(people);
                                    } while (cursor.moveToNext());
                                }
                                cursor.close();
                            }
                        });
                ad.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });
                ad.show();
            }
        }
    }

    // ID查询
    private void queryById(int mId) {
        if (mId == -1) {
            Toast.makeText(DataBaseActivity.this, "请输入ID号", Toast.LENGTH_SHORT)
                    .show();
        } else {
            db = dbHelper.getReadableDatabase();
            People people = new People();
            PeopleList.clear();
            Cursor cursor = db.query("peopleinfo", null, "_id" + "=" + mId,
                    null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor
                            .getColumnIndex("name"));
                    int age = cursor.getInt(cursor.getColumnIndex("age"));
                    float height = cursor.getFloat(cursor
                            .getColumnIndex("height"));
                    people.setID(id);
                    people.setName(name);
                    people.setAge(age);
                    people.setHeight(height);
                    PeopleList.add(people);
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (PeopleList.size() == 0) {
                Toast.makeText(DataBaseActivity.this, "数据库中没有这条数据",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // ID删除
    private void deleteById(int mId) {
        if (mId == -1) {
            Toast.makeText(DataBaseActivity.this, "请输入ID号", Toast.LENGTH_SHORT)
                    .show();
        } else {
            db = dbHelper.getWritableDatabase();
            People people = new People();
            PeopleList.clear();
            Cursor cursor = db.query("peopleinfo", null, "_id" + "=" + mId,
                    null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor
                            .getColumnIndex("name"));
                    int age = cursor.getInt(cursor.getColumnIndex("age"));
                    float height = cursor.getFloat(cursor
                            .getColumnIndex("height"));
                    people.setID(id);
                    people.setName(name);
                    people.setAge(age);
                    people.setHeight(height);
                    PeopleList.add(people);
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (PeopleList.size() == 0) {
                Toast.makeText(DataBaseActivity.this, "数据库中没有这条数据",
                        Toast.LENGTH_SHORT).show();
            } else {
                db.delete("peopleinfo", "_id" + "=" + mId, null);
                Toast.makeText(DataBaseActivity.this, "成功删除" + mId + "这条数据",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 全部删除
    private void deleteData() {
        db = dbHelper.getWritableDatabase();
        db.delete("peopleinfo", null, null);
        PeopleList.clear();
        Toast.makeText(DataBaseActivity.this, "数据清除成功", Toast.LENGTH_SHORT).show();
    }

    // 清除显示
    private void clearListView() {
        PeopleList.clear();
    }

    // 全部显示
    private void showAllData() {
        PeopleList.clear();
        Cursor cursor = db.query("peopleinfo", null, null, null, null, null,
                null);
        if (cursor.moveToFirst()) {
            do {
                People people = new People();
                // 遍历表
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                float height = cursor.getFloat(cursor.getColumnIndex("height"));
                people.setID(id);
                people.setName(name);
                people.setAge(age);
                people.setHeight(height);
                PeopleList.add(people);
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (PeopleList.size() == 0) {
            Toast.makeText(DataBaseActivity.this, "数据库中没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    // 添加数据
    private void addData() {
        if (edt_Name.getText().toString().equals("")
                && edt_Age.getText().toString().equals("")
                && edt_Height.getText().toString().equals("")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(
                    DataBaseActivity.this);
            dialog.setTitle("Warn");
            dialog.setMessage("请输入完整信息");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edt_Name.setText("");
                            edt_Age.setText("");
                            edt_Height.setText("");
                        }
                    });
            dialog.show();
        } else {
            ContentValues values = new ContentValues();
            strName = edt_Name.getText().toString();
            strAge = edt_Age.getText().toString();
            strHeight = edt_Height.getText().toString();
            int iAge = Integer.parseInt(strAge);
            float fHeight = Float.parseFloat(strHeight);
            // 开始组装一条数据
            values.put("name", strName);
            values.put("age", iAge);
            values.put("height", fHeight);

            // 插入数据
            db.insert("peopleinfo", null, values);
            Toast.makeText(DataBaseActivity.this, "数据添加成功", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
//点击EditText之外的位置时隐藏键盘
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v     点击的控件
     * @param event 屏幕事件
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}

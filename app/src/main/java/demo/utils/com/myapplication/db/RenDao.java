package demo.utils.com.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import demo.utils.com.myapplication.entity.RenBean;

/**
 * Created by sts on 2018/3/21.
 */

public class RenDao {

    private final RenOpenHelper renOpenHelper;
    private final SQLiteDatabase db;

    public RenDao(Context context) {
        renOpenHelper = new RenOpenHelper(context, "rendata.db", null, 1);
        db = renOpenHelper.getWritableDatabase();
    }

    public void add(RenBean renBean) {
        Log.e("TAG", "pre");
        db.execSQL("insert into rendata (name,age,phone) values(?,?,?)", new Object[]{renBean.getName(), renBean.getAge(), renBean.getPhone()});
        Log.e("TAG", "after");
    }

    public List<RenBean> getAll() {
        ArrayList<RenBean> rens = new ArrayList<RenBean>();
        Cursor cursor = db.rawQuery("select * from renData", null);
        while (cursor.moveToNext()) {
            RenBean ren = new RenBean();
            ren.setName(cursor.getString(1));
            ren.setAge(cursor.getInt(2));
            ren.setPhone(cursor.getString(3));
            rens.add(ren);
        }
        cursor.close();
        return rens;
    }


}

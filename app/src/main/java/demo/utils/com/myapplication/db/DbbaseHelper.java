package demo.utils.com.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库，实现记账本小程序
 * Created by sts on 2018/1/4.
 */

public class DbbaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE = "create table peopleinfo (_id integer primary key autoincrement,name text not null,age integer,height float);";
    private Context mContext;
    public DbbaseHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists peopleinfo");
        onCreate(db);
    }
}

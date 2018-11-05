package demo.utils.com.myapplication.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sts on 2018/2/28.
 */

public class LianxirenOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_USERDATA = "create table lxrData(name varchar(200) primary key,number varchar(200),introduce varchar(200))";
    private Context mContext;

    public LianxirenOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

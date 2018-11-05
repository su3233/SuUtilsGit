package demo.utils.com.myapplication.db;

import android.util.Log;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import demo.utils.com.myapplication.MyApplication;
import demo.utils.com.myapplication.entity.ChildInfo;

/**
 * Created by sts on 2018/3/26.
 */

public class XutilsDao {

    private final DbManager db;

    public XutilsDao() {
        db = MyApplication.genInstance().xutilsDaoConfig();
    }

    public void insetData() throws DbException {
        //用集合向child_info表中插入多条数据
        ArrayList<ChildInfo> childInfos = new ArrayList<>();
        childInfos.add(new ChildInfo("zhangsan"));
        childInfos.add(new ChildInfo("lisi"));
        childInfos.add(new ChildInfo("wangwu"));
        childInfos.add(new ChildInfo("zhaoliu"));
        childInfos.add(new ChildInfo("qianqi"));
        childInfos.add(new ChildInfo("sunba"));
//db.save()方法不仅可以插入单个对象，还能插入集合
        db.save(childInfos);
    }

    /**
     * 删除数据库
     *
     * @throws DbException
     */
    public void deleteData() throws DbException {
        db.dropDb();
    }

    /**
     * 删除表
     *
     * @throws DbException
     */
    public void deleteTableData() throws DbException {
        //第一种写法：
        db.delete(ChildInfo.class); //child_info表中数据将被全部删除
//第二种写法，添加删除条件：
        WhereBuilder b = WhereBuilder.b();
        b.and("id", ">", 2); //构造修改的条件
        b.and("id", "<", 4);
        db.delete(ChildInfo.class, b);
    }

    /**
     * 添加单一数据
     *
     * @throws DbException
     */
    public void addSingleData(String str) throws DbException {
//        ChildInfo childInfo = new ChildInfo("zhangsan123");
        ChildInfo childInfo = new ChildInfo(str);
        db.save(childInfo);
    }

    /**
     * 修改表中的数据
     *
     * @throws DbException
     */
    public void updateTableData() throws DbException {
        //第一种写法：
        ChildInfo first = db.findFirst(ChildInfo.class);
        first.setcName("zhansan2");
        db.update(first, "c_name"); //c_name：表中的字段名
        //第二种写法：
        WhereBuilder b = WhereBuilder.b();
        b.and("id", "=", first.getId()); //构造修改的条件
        KeyValue name = new KeyValue("c_name", "zhansan3");
        db.update(ChildInfo.class, b, name);
        //第三种写法：
        first.setcName("zhansan4");
        db.saveOrUpdate(first);
    }

    /**
     * 查询表中的数据
     */
    public void findTableData() throws DbException {
        //查询数据库表中第一条数据
        ChildInfo first = db.findFirst(ChildInfo.class);
        Log.i("JAVA", first.toString());
        //添加查询条件进行查询
        List<ChildInfo> all = db.selector(ChildInfo.class).where("id", ">", 2).and("id", "<", 4).findAll();
        for (ChildInfo childInfo : all) {
            Log.i("JAVA", childInfo.toString());
        }
    }


}

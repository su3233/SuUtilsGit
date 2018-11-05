package demo.utils.com.myapplication.greendao;

/**
 * Created by sts on 2018/3/14.
 */

public class EntityManager {
    private static EntityManager entityManager;
    public UserDao userDao;

    /**
     * 创建User表实例
     *
     * @return
     */
    public UserDao getUserDao(){
        userDao = DaoManager.getInstance().getSession().getUserDao();
        return userDao;
    }

    /**
     * 创建单例
     *
     * @return
     */
    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }
}

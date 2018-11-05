package demo.utils.com.myapplication.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by sts on 2018/3/14.
 */
@Entity
public class User {

    @Id
    private Long id;
    private int age;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Generated(hash = 1660313821)
    public User(Long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }
}

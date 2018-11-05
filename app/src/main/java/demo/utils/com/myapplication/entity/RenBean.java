package demo.utils.com.myapplication.entity;

/**
 * Created by sts on 2018/3/21.
 */

public class RenBean {
    public String name;
    public int age;
    public String phone;

    public RenBean() {
    }

    public RenBean(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

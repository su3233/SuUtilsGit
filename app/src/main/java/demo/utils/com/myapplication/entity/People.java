package demo.utils.com.myapplication.entity;

/**
 * Created by sts on 2018/2/28.
 */

public class People {
    public int ID;
    public String Name;
    public int Age;
    public float Height;

    @Override
    public String toString() {
        return "ID=" + ID + ", Name='" + Name + '\'' + ", Age=" + Age + ", Height=" + Height;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
        Height = height;
    }

}

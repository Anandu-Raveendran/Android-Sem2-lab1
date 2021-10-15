package com.example.anandusem2lab1.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userID;
    private String name;
    private int age;
    private Double tuition;
    @TypeConverters(DateTypeConverter.class)
    private Date start_date;

    public User(String name, int age, Double tuition, Date start_date) {
        this.name = name;
        this.age = age;
        this.tuition = tuition;
        this.start_date = start_date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public Double getTuition() {
        return tuition;
    }

    public void setTuition(Double tuition) {
        this.tuition = tuition;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tuition=" + tuition +
                ", start_date=" + start_date +
                '}';
    }
}

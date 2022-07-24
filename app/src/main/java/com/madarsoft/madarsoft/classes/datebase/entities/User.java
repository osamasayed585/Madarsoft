package com.madarsoft.madarsoft.classes.datebase.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;
    private String jonTitle;
    private String gender;

    public User(String name, int age, String jonTitle, String gender) {
        this.name = name;
        this.age = age;
        this.jonTitle = jonTitle;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getJonTitle() {
        return jonTitle;
    }

    public void setJonTitle(String jonTitle) {
        this.jonTitle = jonTitle;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

package com.example.presentationproject;

import android.graphics.drawable.Drawable;

public class PersonalDetailsModal {
    private String firstname;
    private String lastname;
    private String age;
    private int img;

    public PersonalDetailsModal(String myName, String lastname, String age, int img) {
        this.firstname = myName;
        this.lastname = lastname;
        this.age= age;

        this.img= img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

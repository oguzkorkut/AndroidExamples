package com.okorkut.androidui_listview;

public class UserModel {

    String name;
    int age;
    String lastname;
    String team;

    public UserModel(String name, int age, String lastname, String team) {
        this.name = name;
        this.age = age;
        this.lastname = lastname;
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}

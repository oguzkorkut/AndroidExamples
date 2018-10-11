package com.okorkut.activitylifecycle;

public class Model {

    private String name;
    private String lastname;
    private String phoneNo;

    public Model(String name, String lastname, String phoneNo) {
        this.name = name;
        this.lastname = lastname;
        this.phoneNo = phoneNo;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}

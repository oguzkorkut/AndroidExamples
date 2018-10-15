package com.okorkut.realminsert;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Person extends RealmObject {

    private String name;
    private String lastname;
    private Integer salary;
    private Integer age;

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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {

        StringBuilder sb= new StringBuilder("");

        sb.append("name:").append(name).append("\n")
            .append("lastname:").append(lastname).append("\n")
            .append("salary:").append(salary).append("\n")
            .append("age:").append(age);

        return sb.toString();
    }
}

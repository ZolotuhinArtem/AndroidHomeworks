package com.example.arch.databaseexample;

/**
 * Created by arch on 3/18/17.
 */

public class User {

    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;

    private String name;
    private String city;
    private int age;
    private int gender;

    public User(){}

    public User(String name, String city, int age, int gender) {
        this.name = name;
        this.city = city;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

package com.example.arch.hw1_mvvm.model;

/**
 * Created by arch on 06.03.17.
 */

public class User {

    public static final String ATTRIBUTE_FIRST_NAME = "user_first_name";
    public static final String ATTRIBUTE_SECOND_NAME = "user_second_name";
    public static final String ATTRIBUTE_ADDRESS = "user_address";

    private String firstName;
    private String secondName;
    private String address;

    public User(String firstName, String secondName, String address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null)
            return false;
        return address != null ? address.equals(user.address) : user.address == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

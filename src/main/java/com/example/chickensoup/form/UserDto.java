package com.example.chickensoup.form;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    private final Integer id;
    private final String userName;
    private final String department;
    private final String userType;
    private final String userPassword;

    public UserDto(Integer id, String userName, String department, String userType, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.department = department;
        this.userType = userType;
        this.userPassword = userPassword;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDepartment() {
        return department;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userName, entity.userName) &&
                Objects.equals(this.department, entity.department) &&
                Objects.equals(this.userType, entity.userType) &&
                Objects.equals(this.userPassword, entity.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, department, userType, userPassword);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "userName = " + userName + ", " +
                "department = " + department + ", " +
                "userType = " + userType + ", " +
                "userPassword = " + userPassword + ")";
    }
}

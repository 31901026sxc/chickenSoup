package com.example.chickensoup.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "department")
    private String department;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "user_password")
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private Set<UserTestLinkEntity> userTestLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<AnswerSheetEntity> answerSheets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ClassUserLinkEntity> classUserLinks = new LinkedHashSet<>();

    public Set<ClassUserLinkEntity> getClassUserLinks() {
        return classUserLinks;
    }

    public void setClassUserLinks(Set<ClassUserLinkEntity> classUserLinks) {
        this.classUserLinks = classUserLinks;
    }

    public Set<AnswerSheetEntity> getAnswerSheets() {
        return answerSheets;
    }

    public void setAnswerSheets(Set<AnswerSheetEntity> answerSheets) {
        this.answerSheets = answerSheets;
    }

    public Set<UserTestLinkEntity> getUserTestLinks() {
        return userTestLinks;
    }

    public void setUserTestLinks(Set<UserTestLinkEntity> userTestLinks) {
        this.userTestLinks = userTestLinks;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
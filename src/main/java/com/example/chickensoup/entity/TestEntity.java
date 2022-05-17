package com.example.chickensoup.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false)
    private Integer id;

    @Column(name = "test_create_time")
    private Instant testCreateTime;

    @Column(name = "test_start_time")
    private Instant testStartTime;

    @Column(name = "test_end_time")
    private Instant testEndTime;

    @Column(name = "test_status")
    private String testStatus;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "`test_ description`")
    private String testDescription;

    @OneToMany(mappedBy = "test")
    private Set<UserTestLinkEntity> userTestLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "test")
    private Set<AnswerSheetEntity> answerSheets = new LinkedHashSet<>();

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

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public Instant getTestEndTime() {
        return testEndTime;
    }

    public void setTestEndTime(Instant testEndTime) {
        this.testEndTime = testEndTime;
    }

    public Instant getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(Instant testStartTime) {
        this.testStartTime = testStartTime;
    }

    public Instant getTestCreateTime() {
        return testCreateTime;
    }

    public void setTestCreateTime(Instant testCreateTime) {
        this.testCreateTime = testCreateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
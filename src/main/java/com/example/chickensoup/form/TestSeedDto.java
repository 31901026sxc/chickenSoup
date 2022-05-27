package com.example.chickensoup.form;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

public class TestSeedDto implements Serializable {
    private final Integer id;
    private final Instant testCreateTime;
    private final Instant testStartTime;
    private final Instant testEndTime;
    private final String testStatus;
    private final Integer creatorId;
    private final String testDescription;
    private final Set<Integer> questions;

    public TestSeedDto(Integer id, Instant testCreateTime, Instant testStartTime, Instant testEndTime, String testStatus, Integer creatorId, String testDescription, Set<Integer> questions) {
        this.id = id;
        this.testCreateTime = testCreateTime;
        this.testStartTime = testStartTime;
        this.testEndTime = testEndTime;
        this.testStatus = testStatus;
        this.creatorId = creatorId;
        this.testDescription = testDescription;
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public Instant getTestCreateTime() {
        return testCreateTime;
    }

    public Instant getTestStartTime() {
        return testStartTime;
    }

    public Instant getTestEndTime() {
        return testEndTime;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public Set<Integer> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSeedDto entity = (TestSeedDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.testCreateTime, entity.testCreateTime) &&
                Objects.equals(this.testStartTime, entity.testStartTime) &&
                Objects.equals(this.testEndTime, entity.testEndTime) &&
                Objects.equals(this.testStatus, entity.testStatus) &&
                Objects.equals(this.creatorId, entity.creatorId) &&
                Objects.equals(this.testDescription, entity.testDescription) &&
                Objects.equals(this.questions, entity.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testCreateTime, testStartTime, testEndTime, testStatus, creatorId, testDescription, questions);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "testCreateTime = " + testCreateTime + ", " +
                "testStartTime = " + testStartTime + ", " +
                "testEndTime = " + testEndTime + ", " +
                "testStatus = " + testStatus + ", " +
                "creatorId = " + creatorId + ", " +
                "testDescription = " + testDescription + ", " +
                "questions = " + questions + ")";
    }
}

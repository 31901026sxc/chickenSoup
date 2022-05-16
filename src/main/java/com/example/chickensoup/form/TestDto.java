package com.example.chickensoup.form;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class TestDto implements Serializable {
    private final Integer id;
    private final Instant testCreateTime;
    private final Instant testStartTime;
    private final Instant testEndTime;
    private final String testStatus;
    private final Integer creatorId;

    public TestDto(Integer id, Instant testCreateTime, Instant testStartTime, Instant testEndTime, String testStatus, Integer creatorId) {
        this.id = id;
        this.testCreateTime = testCreateTime;
        this.testStartTime = testStartTime;
        this.testEndTime = testEndTime;
        this.testStatus = testStatus;
        this.creatorId = creatorId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDto entity = (TestDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.testCreateTime, entity.testCreateTime) &&
                Objects.equals(this.testStartTime, entity.testStartTime) &&
                Objects.equals(this.testEndTime, entity.testEndTime) &&
                Objects.equals(this.testStatus, entity.testStatus) &&
                Objects.equals(this.creatorId, entity.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testCreateTime, testStartTime, testEndTime, testStatus, creatorId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "testCreateTime = " + testCreateTime + ", " +
                "testStartTime = " + testStartTime + ", " +
                "testEndTime = " + testEndTime + ", " +
                "testStatus = " + testStatus + ", " +
                "creatorId = " + creatorId + ")";
    }
}

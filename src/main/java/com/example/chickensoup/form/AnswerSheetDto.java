package com.example.chickensoup.form;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

public class AnswerSheetDto implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer testId;
    private Instant uploadTime;
    private Integer score;
    private Set<AnswerSheetContentLinkDto> answerSheetContentLinks;

    public AnswerSheetDto(){

    }

    public AnswerSheetDto(Integer id, Integer userId, Integer testId, Instant uploadTime, Integer score, Set<AnswerSheetContentLinkDto> answerSheetContentLinks) {
        this.id = id;
        this.userId = userId;
        this.testId = testId;
        this.uploadTime = uploadTime;
        this.score = score;
        this.answerSheetContentLinks = answerSheetContentLinks;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public Instant getUploadTime() {
        return uploadTime;
    }

    public Integer getScore() {
        return score;
    }

    public Set<AnswerSheetContentLinkDto> getAnswerSheetContentLinks() {
        return answerSheetContentLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheetDto entity = (AnswerSheetDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.testId, entity.testId) &&
                Objects.equals(this.uploadTime, entity.uploadTime) &&
                Objects.equals(this.score, entity.score) &&
                Objects.equals(this.answerSheetContentLinks, entity.answerSheetContentLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, testId, uploadTime, score, answerSheetContentLinks);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "userId = " + userId + ", " +
                "testId = " + testId + ", " +
                "uploadTime = " + uploadTime + ", " +
                "score = " + score + ", " +
                "answerSheetContentLinks = " + answerSheetContentLinks + ")";
    }
}

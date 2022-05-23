package com.example.chickensoup.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "answer_sheet")
public class AnswerSheetEntity {
    @Id
    @Column(name = "answer_sheet_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestEntity test;

    @Column(name = "upload_time")
    private Instant uploadTime;

    @Column(name = "score")
    private Integer score;

    @OneToMany(mappedBy = "answerSheet")
    private Set<AnswerSheetContentLinkEntity> answerSheetContentLinks = new LinkedHashSet<>();

    public Set<AnswerSheetContentLinkEntity> getAnswerSheetContentLinks() {
        return answerSheetContentLinks;
    }

    public void setAnswerSheetContentLinks(Set<AnswerSheetContentLinkEntity> answerSheetContentLinks) {
        this.answerSheetContentLinks = answerSheetContentLinks;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Instant getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Instant uploadTime) {
        this.uploadTime = uploadTime;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
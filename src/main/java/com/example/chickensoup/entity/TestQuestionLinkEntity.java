package com.example.chickensoup.entity;

import javax.persistence.*;

@Entity
@Table(name = "test_question_link")
public class TestQuestionLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id", nullable = false)
    private Integer id;

    @Column(name = "test_id")
    private Integer testId;

    @Column(name = "question_id")
    private Integer questionId;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestion(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
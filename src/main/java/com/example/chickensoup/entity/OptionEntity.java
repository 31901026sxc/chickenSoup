package com.example.chickensoup.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
@Entity
@Table(name = "`option`")
public class OptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "option_content")
    private String optionContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id",insertable = false,updatable = false)
    private QuestionEntity question;

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
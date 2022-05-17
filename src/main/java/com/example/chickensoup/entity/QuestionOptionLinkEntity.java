package com.example.chickensoup.entity;

import javax.persistence.*;

@Entity
@Table(name = "question_option_link")
public class QuestionOptionLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id", nullable = false)
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private OptionEntity option;

    public OptionEntity getOption() {
        return option;
    }

    public void setOption(OptionEntity option) {
        this.option = option;
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
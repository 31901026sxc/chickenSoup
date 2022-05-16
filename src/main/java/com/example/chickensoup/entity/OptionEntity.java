package com.example.chickensoup.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "option")
    private Set<QuestionOptionLinkEntity> questionOptionLinks = new LinkedHashSet<>();

    public Set<QuestionOptionLinkEntity> getQuestionOptionLinks() {
        return questionOptionLinks;
    }

    public void setQuestionOptionLinks(Set<QuestionOptionLinkEntity> questionOptionLinks) {
        this.questionOptionLinks = questionOptionLinks;
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
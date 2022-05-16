package com.example.chickensoup.form;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class QuestionDto implements Serializable {
    private final Integer id;
    private final String questionContent;
    private final String questionType;
    private final String answer;
    private final Integer score;
    private final Set<OptionDto> questionOptionLinks;

    public QuestionDto(Integer id, String questionContent, String questionType, String answer, Integer score, Set<OptionDto> questionOptionLinks) {
        this.id = id;
        this.questionContent = questionContent;
        this.questionType = questionType;
        this.answer = answer;
        this.score = score;
        this.questionOptionLinks = questionOptionLinks;
    }

    public Integer getId() {
        return id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getScore() {
        return score;
    }

    public Set<OptionDto> getQuestionOptionLinks() {
        return questionOptionLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto entity = (QuestionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.questionContent, entity.questionContent) &&
                Objects.equals(this.questionType, entity.questionType) &&
                Objects.equals(this.answer, entity.answer) &&
                Objects.equals(this.score, entity.score) &&
                Objects.equals(this.questionOptionLinks, entity.questionOptionLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionContent, questionType, answer, score, questionOptionLinks);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "questionContent = " + questionContent + ", " +
                "questionType = " + questionType + ", " +
                "answer = " + answer + ", " +
                "score = " + score + ", " +
                "questionOptionLinks = " + questionOptionLinks + ")";
    }
}

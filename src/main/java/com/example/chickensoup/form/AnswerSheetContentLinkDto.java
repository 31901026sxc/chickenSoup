package com.example.chickensoup.form;

import java.io.Serializable;
import java.util.Objects;

public class AnswerSheetContentLinkDto implements Serializable {
    private final Integer id;
    private final Integer answerSheetId;
    private final String answerContent;
    private final QuestionDto question;

    public AnswerSheetContentLinkDto(Integer id, Integer answerSheetId, String answerContent, QuestionDto question) {
        this.id = id;
        this.answerSheetId = answerSheetId;
        this.answerContent = answerContent;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAnswerSheetId() {
        return answerSheetId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public QuestionDto getQuestion() {
        return question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheetContentLinkDto entity = (AnswerSheetContentLinkDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.answerSheetId, entity.answerSheetId) &&
                Objects.equals(this.answerContent, entity.answerContent) &&
                Objects.equals(this.question, entity.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerSheetId, answerContent, question);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "answerSheetId = " + answerSheetId + ", " +
                "answerContent = " + answerContent + ", " +
                "question = " + question + ")";
    }
}

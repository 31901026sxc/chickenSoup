package com.example.chickensoup.form;

import java.io.Serializable;
import java.util.Objects;

public class AnswerSheetDto implements Serializable {
    private final Integer id;
    private final QuestionDto question;
    private final UserDto user;
    private final TestDto test;

    public AnswerSheetDto(Integer id, QuestionDto question, UserDto user, TestDto test) {
        this.id = id;
        this.question = question;
        this.user = user;
        this.test = test;
    }

    public Integer getId() {
        return id;
    }

    public QuestionDto getQuestion() {
        return question;
    }

    public UserDto getUser() {
        return user;
    }

    public TestDto getTest() {
        return test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheetDto entity = (AnswerSheetDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.question, entity.question) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.test, entity.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, user, test);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "question = " + question + ", " +
                "user = " + user + ", " +
                "test = " + test + ")";
    }
}

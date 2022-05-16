package com.example.chickensoup.form;

import java.io.Serializable;
import java.util.Objects;

public class OptionDto implements Serializable {
    private final Integer id;
    private final Integer questionId;
    private final String optionContent;

    public OptionDto(Integer id, Integer questionId, String optionContent) {
        this.id = id;
        this.questionId = questionId;
        this.optionContent = optionContent;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionDto entity = (OptionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.optionContent, entity.optionContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, optionContent);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "questionId = " + questionId + ", " +
                "optionContent = " + optionContent + ")";
    }
}

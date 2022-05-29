package com.example.chickensoup.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
public class AnswerSheetDto implements Serializable {
    private final Integer id;
    private final Integer userId;
    private final Integer testId;
    private final Instant uploadTime;
    private final Integer score;
    private final Set<AnswerSheetContentLinkDto> answerSheetContentLinks;
}

package com.example.chickensoup.repository;

import com.example.chickensoup.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findAllByQuestionContentLike(String keyword);
}
package com.example.chickensoup.repository;

import com.example.chickensoup.entity.AnswerSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerSheetRepository extends JpaRepository<AnswerSheetEntity, Integer> {
    AnswerSheetEntity findByUserAndTest(Integer studentId,Integer testId);
    List<AnswerSheetEntity> findByTest(Integer testId);
}
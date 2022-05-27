package com.example.chickensoup.repository;

import com.example.chickensoup.entity.AnswerSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnswerSheetRepository extends JpaRepository<AnswerSheetEntity, Integer> {
    AnswerSheetEntity findByUserIdAndAndTestId(Integer studentId,Integer testId);

    @Query(value = "select a from AnswerSheetEntity a where a.test.id = ?1")
    List<AnswerSheetEntity> findByTest(Integer testId);

    List<AnswerSheetEntity> findByUserId(Integer testId);
}
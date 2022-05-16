package com.example.chickensoup.repository;

import com.example.chickensoup.entity.AnswerSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerSheetRepository extends JpaRepository<AnswerSheetEntity, Integer> {
}
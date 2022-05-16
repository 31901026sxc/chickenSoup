package com.example.chickensoup.repository;

import com.example.chickensoup.entity.TestQuestionLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestQuestionLinkRepository extends JpaRepository<TestQuestionLinkEntity, Integer> {
}
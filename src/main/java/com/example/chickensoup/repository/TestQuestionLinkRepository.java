package com.example.chickensoup.repository;

import com.example.chickensoup.entity.TestQuestionLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TestQuestionLinkRepository extends JpaRepository<TestQuestionLinkEntity, Integer> {
    Set<TestQuestionLinkEntity> findAllByTestId(Integer testId);
}
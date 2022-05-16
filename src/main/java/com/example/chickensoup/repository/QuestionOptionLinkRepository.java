package com.example.chickensoup.repository;

import com.example.chickensoup.entity.QuestionOptionLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionLinkRepository extends JpaRepository<QuestionOptionLinkEntity, Integer> {
}
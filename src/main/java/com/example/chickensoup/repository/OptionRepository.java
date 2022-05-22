package com.example.chickensoup.repository;

import com.example.chickensoup.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {
    List<OptionEntity> findByQuestionId(Integer questionId);
}
package com.example.chickensoup.repository;

import com.example.chickensoup.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {
}
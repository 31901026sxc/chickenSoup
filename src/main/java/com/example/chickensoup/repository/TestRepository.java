package com.example.chickensoup.repository;

import com.example.chickensoup.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface TestRepository extends JpaRepository<TestEntity, Integer> {
    @Query(value = "SELECT * FROM test where creator_id = ?",nativeQuery = true)
    List<TestEntity> findAllByCreatorId(Integer creatorId);
}
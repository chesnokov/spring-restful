package com.example.persistence.repository;

import com.example.persistence.model.TaskData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskData, Long> {
}

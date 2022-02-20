package com.sideprojects.todo.repository;

import com.sideprojects.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // custom finder methods
    List<Todo> findByCompleted(boolean completed);
    List<Todo> findByTitleContaining(String title);
}

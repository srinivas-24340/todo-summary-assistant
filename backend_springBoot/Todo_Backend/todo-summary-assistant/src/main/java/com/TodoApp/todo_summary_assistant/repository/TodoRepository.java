package com.TodoApp.todo_summary_assistant.repository;

import com.TodoApp.todo_summary_assistant.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}


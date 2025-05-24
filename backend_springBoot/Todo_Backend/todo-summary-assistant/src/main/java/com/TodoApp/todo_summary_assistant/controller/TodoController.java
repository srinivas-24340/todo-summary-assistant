package com.TodoApp.todo_summary_assistant.controller;

import com.TodoApp.todo_summary_assistant.model.Todo;
import com.TodoApp.todo_summary_assistant.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tododb")
@CrossOrigin(origins = "*") // Allows all origins (CORS)
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Test endpoint to verify controller is active
    @GetMapping("/test")
    public String testEndpoint() {
        return "Controller is working!";
    }

    // Get all todos
    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getAllTodos();
    }

    // Create a new todo
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    // Delete a todo by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}

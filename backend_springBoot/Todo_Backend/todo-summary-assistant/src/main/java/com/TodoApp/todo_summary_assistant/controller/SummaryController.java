package com.TodoApp.todo_summary_assistant.controller;

import com.TodoApp.todo_summary_assistant.service.OpenAIService;
import com.TodoApp.todo_summary_assistant.service.SlackService;
import com.TodoApp.todo_summary_assistant.model.Todo;
import com.TodoApp.todo_summary_assistant.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/summarize")
public class SummaryController {

    private final OpenAIService openAIService;
    private final SlackService slackService;
    private final TodoRepository todoRepository;

    public SummaryController(OpenAIService openAIService, SlackService slackService, TodoRepository todoRepository) {
        this.openAIService = openAIService;
        this.slackService = slackService;
        this.todoRepository = todoRepository;
    }

    @PostMapping
    public ResponseEntity<String> summarizeAndSend() {
        try {
            List<Todo> todos = todoRepository.findAll();
            String summary = openAIService.summarizeTodos(todos);
            boolean success = slackService.sendToSlack(summary);
            return success
                    ? ResponseEntity.ok("Summary sent to Slack.")
                    : ResponseEntity.status(500).body("Failed to send to Slack.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

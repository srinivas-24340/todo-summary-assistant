package com.TodoApp.todo_summary_assistant.service;

import com.TodoApp.todo_summary_assistant.model.Todo;
import org.json. JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    public String summarizeTodos(List<Todo> todos) throws Exception {
        String todoText = todos.stream()
                .filter(todo -> !todo.isCompleted())
                .map(Todo::getTitle)
                .collect(Collectors.joining(", "));

        String prompt = "Summarize the following pending tasks: " + todoText;

        JSONObject body = new JSONObject();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", List.of(new JSONObject()
                .put("role", "user")
                .put("content", prompt)));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openai.com/v1/chat/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject responseObject = new JSONObject(response.body());
        return responseObject
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }
}

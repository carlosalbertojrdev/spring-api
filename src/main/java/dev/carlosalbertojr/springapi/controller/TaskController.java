package dev.carlosalbertojr.springapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.carlosalbertojr.springapi.payload.CreateTaskRequest;
import dev.carlosalbertojr.springapi.payload.TaskResponse;
import dev.carlosalbertojr.springapi.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateTaskRequest request) {
        taskService.createNew(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        taskService.deleteById(id);
    }

    @GetMapping("/{id}")
    public TaskResponse find(@PathVariable UUID id) {
        final var task = taskService.findById(id);
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getIsFinished(),
                task.getCreatedAt(),
                task.getUpdatedAt());
    }

    @GetMapping
    public List<TaskResponse> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        final var tasks = taskService.findAll(page, size);
        return tasks.stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getIsFinished(),
                        task.getCreatedAt(),
                        task.getUpdatedAt()))
                .toList();
    }

}

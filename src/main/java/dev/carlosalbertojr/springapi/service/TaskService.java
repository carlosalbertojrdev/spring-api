package dev.carlosalbertojr.springapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.carlosalbertojr.springapi.entity.Task;
import dev.carlosalbertojr.springapi.payload.CreateTaskRequest;
import dev.carlosalbertojr.springapi.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    @Transactional
    public void createNew(CreateTaskRequest request) {
        final var task = new Task(request.title(), request.description());
        taskRepository.save(task);
    }

    public Task findById(UUID id) {
        return taskRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("task not found"));
    }

    public List<Task> findAll(Integer page, Integer size) {
        return taskRepository
                    .findAll(PageRequest.of(page, size))
                    .toList();
    }

    @Transactional
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    } 

}

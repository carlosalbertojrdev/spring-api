package dev.carlosalbertojr.springapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlosalbertojr.springapi.entity.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}

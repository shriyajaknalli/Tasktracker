package com.example.tasktracker;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks()
    {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
            return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask)
    {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTittle(updatedTask.getTittle());
                    task.setDescription(updatedTask.getDescription());
                    task.setCompleted(updatedTask.isCompleted());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id)
    {
          taskRepository.deleteById(id);

    }






}

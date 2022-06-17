package webApplication.Etaskify.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webApplication.Etaskify.resource.task.TaskCreateRequestDto;
import webApplication.Etaskify.resource.task.TaskResponseDto;
import webApplication.Etaskify.service.TaskService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskResponseDto> create(@RequestBody @Valid TaskCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(requestDto));
    }

    @PostMapping("/{taskId}/user/{userId}")
    public ResponseEntity<Void> addTaskForUser(@PathVariable Long taskId,@PathVariable Long userId) {
        log.trace("Added task for user");
        taskService.taskAddForUser(taskId,userId);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.trace("Removing task with id {}", id);
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

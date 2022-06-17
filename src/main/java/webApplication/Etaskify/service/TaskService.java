package webApplication.Etaskify.service;


import webApplication.Etaskify.resource.task.TaskCreateRequestDto;
import webApplication.Etaskify.resource.task.TaskResponseDto;

public interface TaskService {

    TaskResponseDto create(TaskCreateRequestDto requestDto);

    void taskAddForUser(Long taskId,Long userid);

    void delete(Long id);
}

package webApplication.Etaskify.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import webApplication.Etaskify.exception.NameMustBeUniqueException;
import webApplication.Etaskify.exception.TaskNotFoundException;
import webApplication.Etaskify.exception.organization.OrganizationNotFoundException;
import webApplication.Etaskify.model.Organization;
import webApplication.Etaskify.model.Task;
import webApplication.Etaskify.model.User;
import webApplication.Etaskify.repository.OrganizationRepository;
import webApplication.Etaskify.repository.TaskRepository;
import webApplication.Etaskify.repository.UserRepository;
import webApplication.Etaskify.resource.task.TaskCreateRequestDto;
import webApplication.Etaskify.resource.task.TaskResponseDto;
import webApplication.Etaskify.service.TaskService;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;


    @Override
    @Transactional
    public TaskResponseDto create(TaskCreateRequestDto requestDto) {
        Task task = new Task();
        taskRepository.findByTitle(requestDto.getTitle())
                .ifPresent(organization -> {
                    throw new NameMustBeUniqueException(requestDto.getTitle());
                });
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setDeadline(requestDto.getDeadline());
        task.setStatus(requestDto.getStatus());
        task.setOrganization(getOrganisation(requestDto.getOrganizationId()));
        return modelMapper.map(taskRepository.save(task), TaskResponseDto.class);
    }

    @Override
    public void taskAddForUser(Long taskId, Long userid) {
        Task task1 =taskRepository.getById(taskId);
        User user =userRepository.getById(userid);
        task1.getAssignee().add(user);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.trace("Remove task with id {}", id);
        taskRepository.delete(taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    private Organization getOrganisation(Long organizationId) {
        return organizationRepository.findById(organizationId)
                .orElseThrow(OrganizationNotFoundException::new);
    }
}

package com.todolist.demo.service.impl;

import com.todolist.demo.model.Task;
import com.todolist.demo.repository.TaskRepository;
import com.todolist.demo.service.Messages;
import com.todolist.demo.service.TaskManagerService;
import com.todolist.demo.service.exceptions.TaskIsNotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    private final TaskRepository taskRepository;

    private final Messages messages;

    @Autowired
    public TaskManagerServiceImpl(TaskRepository taskRepository, Messages messages) {
        this.taskRepository = taskRepository;
        this.messages = messages;
    }


    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskIsNotException(messages.getMessage("task.exception.taskIsNotExist")));
    }

    @Override
    public List<Task> getTasks(Integer page, Integer pageSize, Map<String, Boolean> sortBy, Map<String, Object> filters) {
        Sort sort = Sort.unsorted();

        for (Map.Entry<String, Boolean> entry : sortBy.entrySet()) {
            String neededField = TaskServiceUtils.getField(entry.getKey());

            if (neededField == null) {
                continue;
            }
            if (entry.getValue()) {
                sort = sort.and(Sort.by(neededField).ascending());
            } else {
                sort = sort.and(Sort.by(neededField).descending());
            }
        }


        Specification<Task> specification = TaskServiceUtils.getSpecifications(filters);


        return taskRepository.findAll(specification, PageRequest.of(page, pageSize, sort)).getContent();
    }

    @Override
    public Task updateTask(Task task) {
        taskRepository.findById(task.getId()).orElseThrow(() -> new TaskIsNotException(messages.getMessage("task.exception.taskIsNotExist")));
        taskRepository.save(task);
        return null;
    }

    @Override
    public void removeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskIsNotException(messages.getMessage("task.exception.taskIsNotExist")));
        taskRepository.delete(task);
    }
}

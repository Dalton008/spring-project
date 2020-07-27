package com.todolist.demo.controllers;

import com.todolist.demo.dto.TaskDTO;
import com.todolist.demo.model.Task;
import com.todolist.demo.service.ConverterDtoToModel;
import com.todolist.demo.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ToDoListController {

    private final TaskManagerService taskManagerService;

    @Autowired
    public ToDoListController(TaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    @PostMapping("/tasks/add/")
    public ResponseEntity addTask(@RequestBody TaskDTO taskDTO)
    {
        Task newTask = ConverterDtoToModel.converterDtoToModel(taskDTO);
        taskManagerService.addTask(newTask);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/tasks/{idTask}")
    public ResponseEntity getTask(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name ="pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) Map<String, Boolean> sortBy,
            @RequestParam(name = "filters", required = false) Map<String, Object> filters,
            @PathVariable Long idTask
            ) {
        if ((page == null || pageSize == null) && (sortBy == null || sortBy.isEmpty()) && (filters == null ||filters.isEmpty())) {
            if (idTask == null) {
                return ResponseEntity.ok(taskManagerService.getTasks());
            } else {
                return ResponseEntity.ok(taskManagerService.getTask(idTask));
            }
        } else {
            return ResponseEntity.ok(taskManagerService.getTasks(page, pageSize, sortBy, filters));
        }
    }

    @DeleteMapping (value = "/tasks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@RequestParam Long id){
        taskManagerService.removeTask(id);
        return ResponseEntity.noContent().build();

   }

    @PutMapping (value = "/tasks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO){
        Task task = ConverterDtoToModel.converterDtoToModel(taskDTO);
        taskManagerService.updateTask(task);
        return ResponseEntity.ok(taskManagerService.updateTask(task));

    }

}

package com.todolist.demo.service;

import com.todolist.demo.dto.TaskDTO;
import com.todolist.demo.model.Task;

public class ConverterDtoToModel {
    public static Task converterDtoToModel(TaskDTO taskDTO){
        return Task.builder().
                id(taskDTO.getId())
                .name(taskDTO.getName())
                .start(taskDTO.getStart())
                .end(taskDTO.getEnd())
                .build();
    }
}

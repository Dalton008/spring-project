package com.todolist.demo.controllers;

import com.todolist.demo.model.Task;
import com.todolist.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@Controller
public class DefaultController {
    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String home(Model model){
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : taskIterable){
            tasks.add(task);
        }
        model.addAttribute("title", "ToDoList Главная страница");
        return "home";
    }

    @RequestMapping("/tasks")
    public String tasks(Model model){
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : taskIterable){
            tasks.add(task);
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("title", "Задачи");

        return "tasks";
    }
}

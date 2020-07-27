package com.todolist.demo.service.exceptions;

public class TaskIsNotException extends NullPointerException{
    public TaskIsNotException(String msg){
        super(msg);
    }
}

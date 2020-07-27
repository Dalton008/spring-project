//package com.todolist.demo.service;
//
//import com.todolist.demo.model.Task;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class Storage {
//    private static int currentId = 1;
//    private static HashMap<Integer, Task> tasks = new HashMap();
//
//    public static List<Task> getAllTask(){
//        ArrayList<Task> taskList = new ArrayList<>();
//        taskList.addAll(tasks.values());
//        return taskList;
//    }
//
//    public static int addTask(Task task){
//        int id = currentId++;
//        task.setId(id);
//        tasks.put(id, task);
//        return id;
//    }
//
//    public static Task getTask(int taskId){
//        if(tasks.containsKey(taskId)){
//            return tasks.get(taskId);
//        }
//        return null;
//    }
//
//    public static void deleteTask(int taskId){
//        tasks.remove(taskId);
//    }
//
//    public static void update(int taskId, Task task){
//        tasks.put(taskId, task);
//    }
//
//    public static void deleteAllTasks(){
//        tasks.clear();
//    }
//}

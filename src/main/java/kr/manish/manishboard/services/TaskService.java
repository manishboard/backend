package kr.manish.manishboard.services;

import java.util.List;

import kr.manish.manishboard.dtos.TaskDto;

public interface TaskService {
    List<TaskDto> getAllTask(String userName);
    TaskDto getTask(String taskId);
    TaskDto addTask(TaskDto TaskDto);
    TaskDto updateTask(TaskDto TaskDto);
    String deleteTask(String taskId);
    List<TaskDto> searchTaskByDetails(String keyWords);
}

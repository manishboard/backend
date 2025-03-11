package kr.manish.manishboard.service;

import kr.manish.manishboard.model.Task;
import kr.manish.manishboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }
}
package kr.manish.manishboard.controller;

import kr.manish.manishboard.model.Task;
import kr.manish.manishboard.model.User;
import kr.manish.manishboard.security.JwtTokenUtil;
import kr.manish.manishboard.service.TaskService;
import kr.manish.manishboard.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Task>> getAllTasks(@RequestHeader("Authorization") String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ", ""));
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(taskService.getAllTasksByUser(user.getId()));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Task> createTask(@RequestHeader("Authorization") String token, @RequestBody Task task) {
        String username = jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ", ""));
        User user = userService.findByUsername(username);
        task.setUser(user);
        return ResponseEntity.ok(taskService.save(task));
    }
}
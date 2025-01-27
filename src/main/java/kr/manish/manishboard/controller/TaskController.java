package kr.manish.manishboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.manish.manishboard.dtos.ApiResponse;
import kr.manish.manishboard.dtos.TaskDto;
import kr.manish.manishboard.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //get all
    @GetMapping("/{userName}")
    public ResponseEntity<List<TaskDto>> getAllTask(@PathVariable String userName){
        List<TaskDto> newTaskDtoList = taskService.getAllTask(userName);
        return ResponseEntity.ok(newTaskDtoList);
    }
    //get one
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable String taskId){
        TaskDto newTaskDto = taskService.getTask(taskId);
        return ResponseEntity.ok(newTaskDto);
    }
    
    //create
    @PostMapping
    public ResponseEntity<TaskDto> addTask(@Valid @RequestBody TaskDto TaskDto){
        TaskDto newTaskDto = taskService.addTask(TaskDto);
        return new ResponseEntity<TaskDto>(newTaskDto , HttpStatus.CREATED);
    } 
    //update
    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@Valid  @RequestBody TaskDto TaskDto){
        TaskDto newTaskDto = taskService.updateTask(TaskDto);
        return ResponseEntity.ok(newTaskDto);
    }
    //delete
    @DeleteMapping("/{userName}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable String taskId){
        return ResponseEntity.ok(ApiResponse.builder()
                                    .message(taskService.deleteTask(taskId))
                                    .success(true)
                                    .build() );
    }
    //search
    @GetMapping("/search/{keyWords}")
    public ResponseEntity<List<TaskDto>> searchTaskByDetails(@PathVariable String keyWords){
        List<TaskDto> newTaskDtoList = taskService.searchTaskByDetails(keyWords);
        return ResponseEntity.ok(newTaskDtoList);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException exp){
        logger.info("Runtime Exception Generated: {}" ,exp.getMessage() );
        return new ResponseEntity<ApiResponse>( ApiResponse.builder()
                                                            .message("Runtime Exception Generated: " + exp.getMessage() )
                                                            .success(false)
                                                            .build(), HttpStatus.NOT_FOUND);
    }
}

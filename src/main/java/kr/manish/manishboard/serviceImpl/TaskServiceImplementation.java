package kr.manish.manishboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import kr.manish.manishboard.dtos.TaskDto;
import kr.manish.manishboard.dtos.TaskDto;
import kr.manish.manishboard.entities.Task;
import kr.manish.manishboard.exceptions.ResourceNotFoundException;
import kr.manish.manishboard.repositories.TaskRepository;
import kr.manish.manishboard.services.TaskService;

@Service
@Primary
public class TaskServiceImplementation implements TaskService{

    private List<Task> taskList = new ArrayList<>();
    
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper mapper;

    private Logger logger = LoggerFactory.getLogger(TaskServiceImplementation.class);
    

    @Override
    public List<TaskDto> getAllTask(String taskName){
        List<Task> allTasks = taskRepository.findAll();

        return allTasks.stream()
                        .map( task -> mapper.map(task, TaskDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(String taskId){
        Task task = taskRepository.findById(taskId)
                                    .orElseThrow(() -> new ResourceNotFoundException("Task With given Task Id does not Exists"));
        return mapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto addTask(TaskDto taskDto){

        Task task = mapper.map(taskDto, Task.class);
        task.setId(UUID.randomUUID().toString());
        Task savedTask = taskRepository.save(task);

        logger.info("Task is added Successfully") ;
        return mapper.map(savedTask, TaskDto.class);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto){
        
        if( !taskRepository.existsById( taskDto.getId() ) ) throw new ResourceNotFoundException("task with given Task Id not found");
        Task task = mapper.map(taskDto, Task.class);
        taskRepository.save(task);
        return mapper.map(task, TaskDto.class);
    }

    @Override
    public String deleteTask(String taskId){
        taskRepository.deleteById(taskId);
        if( taskRepository.existsById(taskId) ) throw new RuntimeException("Task was not deleted. There is some error!!!");
        return "Task Deleted Successfully";
    }

    @Override
    public List<TaskDto> searchTaskByDetails(String keyWords){
        List<Task> allTaskList = taskRepository.findByDetailsContaining(keyWords);
        return allTaskList.stream()
                            .map(task -> mapper.map(task, TaskDto.class))
                            .collect(Collectors.toList());
    }
}
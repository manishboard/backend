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
import kr.manish.manishboard.dtos.UserDto;
import kr.manish.manishboard.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //get all
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> newUserDtoList = userService.getAllUser();
        return ResponseEntity.ok(newUserDtoList);
    } 
    //get one
    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userName){
        UserDto newUserDto = userService.getUser(userName);
        return ResponseEntity.ok(newUserDto);
    } 
    
    //create
    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto){
        UserDto newUserDto = userService.addUser(userDto);
        return new ResponseEntity<UserDto>(newUserDto , HttpStatus.CREATED);
    } 
    //update
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto userDto){
        UserDto newUserDto = userService.updateUser(userDto);
        return ResponseEntity.ok(newUserDto);
    }
    //delete
    @DeleteMapping("/{userName}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userName){
        return ResponseEntity.ok(ApiResponse.builder()
                                    .message(userService.deleteUser(userName))
                                    .success(true)
                                    .build() );
    }
    //search
    @GetMapping("/search/{keyWords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyWords){
        List<UserDto> newUserDtoList = userService.searchUser(keyWords);
        return ResponseEntity.ok(newUserDtoList);
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

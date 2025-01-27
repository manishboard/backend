package kr.manish.manishboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import kr.manish.manishboard.dtos.UserDto;
import kr.manish.manishboard.entities.User;
import kr.manish.manishboard.exceptions.ResourceNotFoundException;
import kr.manish.manishboard.repositories.UserRepository;
import kr.manish.manishboard.services.UserService;

@Service
@Primary
public class UserServiceImplementation implements UserService{

    private List<User> userList = new ArrayList<>();
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    private Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
    


    @Override
    public List<UserDto> getAllUser(){
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                        .map( user -> mapper.map(user, UserDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(String userName){
        User user = userRepository.findById(userName)
                                    .orElseThrow(() -> new ResourceNotFoundException("User With given User Name does not Exists"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto addUser(UserDto userDto){
        Boolean isUserExist = userRepository.existsById(userDto.getUserName());
        if(isUserExist) throw new RuntimeException("User with given User Name Already Exists");

        User user = mapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);

        logger.info("User is added Successfully") ;
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto){
        
        if( !userRepository.existsById( userDto.getUserName() ) ) throw new ResourceNotFoundException("user with given User Name not found");
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public String deleteUser(String userName){
        userRepository.deleteById(userName);
        if( userRepository.existsById(userName) ) throw new RuntimeException("User was not deleted. There is some error!!!");
        return "User Deleted Successfully";
    }

    @Override
    public List<UserDto> searchUser(String keyWords){
        List<User> allUserList = userRepository.findByUserNameContaining(keyWords);
        return allUserList.stream()
                            .map(user -> mapper.map(user, UserDto.class))
                            .collect(Collectors.toList());


        
    }
}
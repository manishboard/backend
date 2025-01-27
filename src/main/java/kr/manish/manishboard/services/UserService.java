package kr.manish.manishboard.services;

import java.util.List;
import kr.manish.manishboard.dtos.UserDto;

public interface UserService {
    List<UserDto> getAllUser();
    UserDto getUser(String userName);
    UserDto addUser(UserDto userDto );
    UserDto updateUser(UserDto userDto);
    String deleteUser(String userName);
    List<UserDto> searchUser(String keyWords);
}

package ma.website.blog.service;

import ma.website.blog.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUser();
    UserDto getUser(Integer userId);
    void deleteUser(Integer userId);
    UserDto updateUser(UserDto userDto , Integer userID);


}

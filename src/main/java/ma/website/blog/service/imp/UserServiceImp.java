package ma.website.blog.service.imp;

import lombok.AllArgsConstructor;
import ma.website.blog.dto.UserDto;
import ma.website.blog.entity.User;
import ma.website.blog.exeption.ResourceNotFoundExceptrion;
import ma.website.blog.repository.UserRepository;
import ma.website.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto , User.class);
        return this.modelMapper.map(this.userRepository.save(user),UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUsers = this.userRepository.findAll();
        return allUsers.stream()
                .map(user -> this.modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("user", "id", userId));
        return modelMapper.map(user , UserDto.class) ;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("user", "id", userId));
        this.userRepository.delete(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("user", "id", userId));

        userDto.setId(userId);
        User updatedUser = this.userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(updatedUser, UserDto.class);
    }


}

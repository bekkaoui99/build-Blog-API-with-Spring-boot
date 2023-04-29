package ma.website.blog.web;


import lombok.AllArgsConstructor;
import ma.website.blog.dto.UserDto;
import ma.website.blog.payloads.ApiResponse;
import ma.website.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserControler {

    private final UserService userService;

    //get All users :
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUser = this.userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping(path = "/{userid}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userid") Integer userid){
        UserDto userdto = this.userService.getUser(userid);
        return ResponseEntity.ok(userdto);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(this.userService.createUser(userDto) , HttpStatus.CREATED);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userid") Integer userid){
        this.userService.deleteUser(userid);
        return new  ResponseEntity<ApiResponse>(new ApiResponse("user deleted seccusefully",true),HttpStatus.OK);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto ,
                                              @PathVariable("userid") Integer userid){
        
        UserDto updatedUser = this.userService.updateUser(userDto, userid);
        return new  ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

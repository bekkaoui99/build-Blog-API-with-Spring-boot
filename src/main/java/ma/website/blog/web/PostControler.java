package ma.website.blog.web;


import lombok.AllArgsConstructor;
import ma.website.blog.dto.CategoryDto;
import ma.website.blog.dto.PostDto;
import ma.website.blog.dto.UserDto;
import ma.website.blog.payloads.ApiResponse;
import ma.website.blog.service.CategoryService;
import ma.website.blog.service.PostService;
import ma.website.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PostControler {

    private final PostService postService;
    private final UserService userService;
    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    // create post :

    @PostMapping("/user/{userid}/category/{categoryid}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
                                              @PathVariable("userid") Integer userid,
                                              @PathVariable("categoryid") Integer categoryid
                                              ){

        UserDto userDto = this.userService.getUser(userid);
        CategoryDto categoryDto = this.categoryService.getCategory(categoryid);

        postDto.setUser(userDto);
        postDto.setCategory(categoryDto);
        PostDto createdPost = this.postService.createPost(postDto);
        return new  ResponseEntity<>(createdPost , HttpStatus.CREATED);

    }


    @GetMapping("/user/{userid}/posts")
    public ResponseEntity<List<PostDto>> allPostByUser(@PathVariable("userid")Integer userid){

        List<PostDto> allpostDtos = this.postService.allPostByUser(userid);

        return new  ResponseEntity<>(allpostDtos, HttpStatus.OK);
    }


    @GetMapping("/category/{categoryid}/posts")
    public ResponseEntity<List<PostDto>> allPostByCategory(@PathVariable("categoryid") Integer categoryid){

        List<PostDto> allpostDtos = this.postService.allPostByCategory(categoryid);

        return new  ResponseEntity<>(allpostDtos, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> allPosts(){

        List<PostDto> allpostDtos = this.postService.getAllPost();

        return new  ResponseEntity<>(allpostDtos, HttpStatus.OK);
    }


    @GetMapping("/posts{postid}")
    public ResponseEntity<PostDto> getPost(@PathVariable("postid") Integer postid){

        PostDto postDtos = this.postService.getPost(postid);

        return new  ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @DeleteMapping("/posts{postid}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postid") Integer postid){

        this.postService.deletePost(postid);

        return new  ResponseEntity<>( new ApiResponse("post deleted seccusfuly" , true) , HttpStatus.OK);
    }

    @PutMapping("/posts{postid}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody PostDto postDto,
            @PathVariable("postid") Integer postid){

        PostDto updatedPost = this.postService.updatePost(postDto, postid);

        return new  ResponseEntity<>(updatedPost , HttpStatus.OK);
    }


}

package ma.website.blog.service;

import ma.website.blog.dto.CategoryDto;
import ma.website.blog.dto.PostDto;
import ma.website.blog.dto.UserDto;
import ma.website.blog.entity.Category;
import ma.website.blog.entity.Post;
import ma.website.blog.entity.User;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPost();
    PostDto getPost(Integer postId);
    void deletePost(Integer postId);
    PostDto updatePost(PostDto postDto , Integer postId);

    List<PostDto> allPostByUser(Integer userid);

    List<PostDto> allPostByCategory(Integer categoryid);



}

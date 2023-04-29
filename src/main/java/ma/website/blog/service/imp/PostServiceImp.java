package ma.website.blog.service.imp;

import lombok.AllArgsConstructor;

import ma.website.blog.dto.PostDto;

import ma.website.blog.entity.Category;
import ma.website.blog.entity.Post;
import ma.website.blog.entity.User;
import ma.website.blog.exeption.ResourceNotFoundExceptrion;
import ma.website.blog.repository.CategoryRepository;
import ma.website.blog.repository.PostRepository;
import ma.website.blog.repository.UserRepository;

import ma.website.blog.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = this.postRepository.save(this.modelMapper.map(postDto, Post.class));
        return modelMapper.map(post , PostDto.class) ;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> allPost = this.postRepository.findAll();
        return allPost.stream()
                .map(post -> this.modelMapper.map(post , PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("post", "id", postId));
        return modelMapper.map(post , PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("post", "id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("post", "id", postId));

        postDto.setId(post.getId());

        Post updatePost = this.postRepository.save(this.modelMapper.map(postDto, Post.class));
        return this.modelMapper.map(updatePost , PostDto.class);
    }

    @Override
    public List<PostDto> allPostByUser(Integer userid) {
        User user = this.userRepository.findById(userid).
                orElseThrow(() -> new ResourceNotFoundExceptrion("user", "id", userid));

        List<Post> allposts = this.postRepository.findByUser(user);

        return allposts.stream()
                .map(post -> this.modelMapper.map(post ,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> allPostByCategory(Integer categoryid) {
        Category category = this.categoryRepository.findById(categoryid).
                orElseThrow(() -> new ResourceNotFoundExceptrion("category", "id", categoryid));

        List<Post> allposts = this.postRepository.findByCategory(category);

        return allposts.stream()
                .map(post -> this.modelMapper.map(post ,PostDto.class))
                .collect(Collectors.toList());
    }


}

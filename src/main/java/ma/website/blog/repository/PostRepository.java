package ma.website.blog.repository;


import ma.website.blog.entity.Category;
import ma.website.blog.entity.Post;
import ma.website.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post , Integer> {

    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);

}

package ma.website.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.website.blog.entity.Category;
import ma.website.blog.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDto {

    private Integer id;
    private String title;
    private String image;
    private String description;
    private CategoryDto category;
    private UserDto user;
}

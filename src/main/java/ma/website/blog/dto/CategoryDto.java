package ma.website.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.website.blog.entity.Post;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private Integer id;
    private String name;
    private String descriptin;
    private List<PostDto> postList ;
}

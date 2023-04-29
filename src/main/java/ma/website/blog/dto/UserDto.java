package ma.website.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.website.blog.entity.Post;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Integer id;
    @NotEmpty(message = "something went wrong : username must be empty")
    private String userName;
    private String lastName;
    @Email(message = "invalide email.")
    private String email;
    @Min(value = 8,message = "you should write min 8 character")
    private String password;
    private Date birthday;

    private List<PostDto> postList = new ArrayList<>() ;
}

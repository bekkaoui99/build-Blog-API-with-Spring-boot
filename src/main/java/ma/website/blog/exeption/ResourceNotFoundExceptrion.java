package ma.website.blog.exeption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundExceptrion extends RuntimeException {

    private String resourceName ;
    private String fieldName ;
    private Integer fielvalue ;
    public ResourceNotFoundExceptrion(String resourceName ,String fieldName , Integer fielvalue ){
        super( resourceName +" not found with " + fieldName +" : " + fielvalue);
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fielvalue = fielvalue;
    }

}

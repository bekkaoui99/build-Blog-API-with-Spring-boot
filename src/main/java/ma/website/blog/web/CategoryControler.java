package ma.website.blog.web;


import lombok.AllArgsConstructor;
import ma.website.blog.dto.CategoryDto;
import ma.website.blog.payloads.ApiResponse;
import ma.website.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryControler {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;


    // create category :

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(this.categoryService.getAllCategory() , HttpStatus.OK);
    }


    @GetMapping("/{categoryid}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryid") Integer categoryid ){
        return new ResponseEntity<>(this.categoryService.getCategory(categoryid), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryid}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryid") Integer categoryid){
        this.categoryService.deleteCategory(categoryid);
        return new  ResponseEntity<>(new ApiResponse("category deleted seccusfuly",true),HttpStatus.OK);
    }

    @PostMapping("/{categoryid}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto ,
                                                      @PathVariable("categoryid") Integer categoryid){

        CategoryDto category = this.categoryService.updateCategory(categoryDto , categoryid);
        return new ResponseEntity<>(category , HttpStatus.OK);
    }


}

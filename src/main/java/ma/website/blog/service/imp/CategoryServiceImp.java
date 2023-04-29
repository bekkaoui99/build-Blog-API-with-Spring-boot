package ma.website.blog.service.imp;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import ma.website.blog.dto.CategoryDto;
import ma.website.blog.entity.Category;
import ma.website.blog.exeption.ResourceNotFoundExceptrion;
import ma.website.blog.repository.CategoryRepository;
import ma.website.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {

    private  final CategoryRepository categoryRepository;
    private  final ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepository.save(this.modelMapper.map(categoryDto, Category.class));
        return  modelMapper.map(category , CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> allCategory = this.categoryRepository.findAll();
        return allCategory.stream()
                .map(category -> this.modelMapper.map(category , CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(Integer categoryID) {
        Category category = this.categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("category", "id", categoryID));
        return modelMapper.map(category , CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryID) {

        Category category = this.categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("category", "id", categoryID));

        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryID) {
        Category category = this.categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundExceptrion("category", "id", categoryID));
        categoryDto.setId(categoryID);
        Category updatedCategory = this.categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(updatedCategory , CategoryDto.class);
    }
}

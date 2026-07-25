package com.shahid.shopsphere.service.imp1;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shahid.shopsphere.dto.category.CategoryRequest;
import com.shahid.shopsphere.dto.category.CategoryResponse;
import com.shahid.shopsphere.entity.Category;
import com.shahid.shopsphere.exception.CategoryAlreadyExistsException;
import com.shahid.shopsphere.exception.ResourceNotFoundException;
import com.shahid.shopsphere.repository.CategoryRepository;
import com.shahid.shopsphere.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImp1 implements CategoryService {

    private final CategoryRepository categoryRepository;


    private CategoryResponse mapToResponse(Category category)
    {
        return   CategoryResponse.builder()
                               .id(category.getId())
                               .name(category.getName())
                               .description(category.getDescription())
                               .active(category.getActive())
                               .createdAt(category.getCreatedAt())
                               .updatedAt(category.getUpdatedAt())
                               .build();

    }
   @Override
    public CategoryResponse createCategory(CategoryRequest request)
    {
        //fetch from db that category already exists
        Optional<Category> existingCategory = categoryRepository.findByName(request.getName());

        if(existingCategory.isPresent())
        {
         throw new CategoryAlreadyExistsException("Category Already Exists.");
        }
        //Dto->entity
        Category category = Category.builder()
                               .name(request.getName())
                               .description(request.getDescription())
                                .build();
        //save
        Category savedCategory = categoryRepository.save(category);

        return mapToResponse(savedCategory);
        
    }
   @Override
    public List<CategoryResponse> getAllCategories()
    {
        return categoryRepository.findAll()
                            .stream()
                            .map(category -> mapToResponse(category))
                             .toList();
                                    
    }
   @Override
    public CategoryResponse getCategoryById(Long id)
    {
      Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not Found With Id:" + id));

       return mapToResponse(category);
    }
    
    @Override
    public  CategoryResponse updateCategory(Long id,CategoryRequest request){
     
         Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not Found With Id:" + id));
         
         category.setName(request.getName());
         category.setDescription(request.getDescription());

         Category updatedCategory = categoryRepository.save(category);
           
         return mapToResponse(updatedCategory);


    }
    @Override
    public void deleteCategory(Long id)
    {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not Found With Id:" + id));

        
            categoryRepository.delete(existingCategory);
        
    }

}

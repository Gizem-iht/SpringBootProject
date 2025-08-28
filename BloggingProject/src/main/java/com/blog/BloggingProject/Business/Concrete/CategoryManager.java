package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.CategoryService;
import com.blog.BloggingProject.Core.Mapping.ModelMapperService.ModelMapperService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dtos.CategoryDto.CategoryListDto;
import com.blog.BloggingProject.Entities.Concretes.Category;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryAlreadyExistException;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryNotFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Repositories.CategoryRepository;
import com.blog.BloggingProject.Request.CategoryRequest.CategoryCreateRequest;
import com.blog.BloggingProject.Request.CategoryRequest.CategoryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CategoryListDto>> getAll() throws NoDataFoundException {
        List<CategoryListDto> dtoList = categoryRepository.findAll()
                .stream()
                .map(category -> this.modelMapperService.forDto().map(category, CategoryListDto.class))
                //modelmapper sayesinde c.getId(), c.getName() şeklinde yazmadık(Gizem)
                .collect(Collectors.toList());

        checkIfListEmpty(dtoList);

        return new SuccessDataResult<>(dtoList, "Categories listed successfully.");
    }

    @Override
    public Result createCategory(CategoryCreateRequest createRequest) throws CategoryAlreadyExistException {

        checkIsNotExistByCategoryName(createRequest.getName());

        Category category = this.modelMapperService.forRequest().map(createRequest, Category.class);
        //createRequesti içindeki alanları aldık ve category nesnesine çevirdik.(Gizem)
        categoryRepository.save(category);

        return new SuccessResult("Category created successfully.");

    }

    @Override
    public Result updateCategory(CategoryUpdateRequest updateCategoryRequest)
            throws CategoryNotFoundException, CategoryAlreadyExistException {

        checkIsExistsByCategoryId(updateCategoryRequest.getCategoryId());
        checkIsNotExistByCategoryName(updateCategoryRequest.getName());

        Category existingCategory = categoryRepository.findById(updateCategoryRequest.getCategoryId()).get();

        this.modelMapperService.forRequest().map(updateCategoryRequest, existingCategory);

        categoryRepository.save(existingCategory);

        return new SuccessResult("Category updated successfully. ID: " + updateCategoryRequest.getCategoryId());
    }

    @Override
    public Result deleteCategory(int id) throws CategoryNotFoundException {
        checkIsExistsByCategoryId(id);
        categoryRepository.deleteById(id);
        return new SuccessResult("Category deleted successfully. ID: " + id);
    }

    @Override
    public void checkIsExistsByCategoryId(int id) throws CategoryNotFoundException {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found with ID: " + id);
        }
    }

    @Override
    public void checkIfListEmpty(List<?> list) throws NoDataFoundException {
        if (list == null || list.isEmpty()) {
            throw new NoDataFoundException("No data found in the system.");
        }
    }

    @Override
    public void checkIsNotExistByCategoryName(String name) throws CategoryAlreadyExistException {
        if (categoryRepository.existsByName(name)) {
            throw new CategoryAlreadyExistException("Category already exists with name: " + name);
        }
    }
}

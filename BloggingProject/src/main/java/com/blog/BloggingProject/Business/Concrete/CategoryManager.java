package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.CategoryService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryCreateRequest;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryListRequest;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryUpdateRequest;
import com.blog.BloggingProject.Entities.Concretes.Category;
import com.blog.BloggingProject.Exception.CategoryException.CategoryAlreadyExistException;
import com.blog.BloggingProject.Exception.CategoryException.CategoryNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public DataResult<List<CategoryListRequest>> getAll() throws NoDataFoundException {
        List<CategoryListRequest> dtoList = categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryListRequest(c.getId(), c.getName()))
                .collect(java.util.stream.Collectors.toList());

        checkIfListEmpty(dtoList);

        return new SuccessDataResult<>(dtoList, "Categories listed successfully.");
    }

    @Override
    public Result createCategory(CategoryCreateRequest createRequest) throws CategoryAlreadyExistException{

        checkIsNotExistByCategoryName(createRequest.getName());

        Category category = new Category();
        category.setName(createRequest.getName());
        categoryRepository.save(category);

        return new SuccessResult("Category created successfully.");
    }

    @Override
    public Result updateCategory(CategoryUpdateRequest updateCategoryRequest) throws CategoryNotFoundException,CategoryAlreadyExistException {
        checkIsExistsByCategoryId(updateCategoryRequest.getCategoryId());

        checkIsNotExistByCategoryName(updateCategoryRequest.getName());

        Category existingCategory = categoryRepository.findById(updateCategoryRequest.getCategoryId()).get();
        existingCategory.setName(updateCategoryRequest.getName());
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


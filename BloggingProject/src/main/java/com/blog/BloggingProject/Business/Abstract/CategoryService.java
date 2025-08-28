package com.blog.BloggingProject.Business.Abstract;

import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dtos.CategoryDto.CategoryListDto;
import com.blog.BloggingProject.Request.CategoryRequest.CategoryCreateRequest;
import com.blog.BloggingProject.Request.CategoryRequest.CategoryUpdateRequest;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryAlreadyExistException;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryNotFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;

import java.util.List;

public interface CategoryService {
    DataResult<List<CategoryListDto>> getAll() throws NoDataFoundException;

    Result createCategory(CategoryCreateRequest createRequest) throws CategoryAlreadyExistException;

    Result updateCategory(CategoryUpdateRequest updateCategoryRequest) throws CategoryNotFoundException,CategoryAlreadyExistException;

    Result deleteCategory(int id) throws CategoryNotFoundException;

    void checkIsExistsByCategoryId(int id) throws CategoryNotFoundException;

    void checkIfListEmpty(List<?> list) throws NoDataFoundException;

    void checkIsNotExistByCategoryName(String name) throws CategoryAlreadyExistException;
}

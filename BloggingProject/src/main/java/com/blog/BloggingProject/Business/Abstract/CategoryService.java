package com.blog.BloggingProject.Business.Abstract;

import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryCreateRequest;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryListRequest;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryUpdateRequest;
import com.blog.BloggingProject.Exception.CategoryException.CategoryAlreadyExistException;
import com.blog.BloggingProject.Exception.CategoryException.CategoryNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;

import java.util.List;

public interface CategoryService {
    DataResult<List<CategoryListRequest>> getAll() throws NoDataFoundException;

    Result createCategory(CategoryCreateRequest createRequest) throws CategoryAlreadyExistException;

    Result updateCategory(CategoryUpdateRequest updateCategoryRequest) throws CategoryNotFoundException,CategoryAlreadyExistException;

    Result deleteCategory(int id) throws CategoryNotFoundException;

    void checkIsExistsByCategoryId(int id) throws CategoryNotFoundException;

    void checkIfListEmpty(List<?> list) throws NoDataFoundException;

    void checkIsNotExistByCategoryName(String name) throws CategoryAlreadyExistException;
}

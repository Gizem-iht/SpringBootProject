package com.blog.BloggingProject.Business.Abstract;

import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dtos.PostDto.PostListDto;
import com.blog.BloggingProject.Request.PostRequest.PostCreateRequest;
import com.blog.BloggingProject.Request.PostRequest.PostUpdateRequest;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryNotFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.PostExceptions.PostNotFoundException;

import java.util.List;

public interface PostService {
    DataResult<List<PostListDto>> getAll() throws NoDataFoundException;

    Result createPost(PostCreateRequest postRequest) throws CategoryNotFoundException;

    Result updatePost(int id, PostUpdateRequest postRequest)
            throws PostNotFoundException, CategoryNotFoundException;

    Result deletePost(int id) throws PostNotFoundException;

    void checkIsExistsByPostId(int id) throws PostNotFoundException;

    void checkIsExistsByCategoryId(int id) throws CategoryNotFoundException;

    void checkIfListEmpty(List<?> list) throws NoDataFoundException;
}

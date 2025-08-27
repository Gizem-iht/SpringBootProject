package com.blog.BloggingProject.Business.Abstract;

import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dto.CommentDto.CommentCreateRequest;
import com.blog.BloggingProject.Dto.CommentDto.CommentListRequest;
import com.blog.BloggingProject.Dto.CommentDto.CommentUpdateRequest;
import com.blog.BloggingProject.Exception.CommentException.CommentNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.PostException.PostNotFoundException;

import java.util.List;

public interface CommentService {
    DataResult<List<CommentListRequest>> getAll() throws NoDataFoundException;

    Result createComment(CommentCreateRequest request) throws PostNotFoundException;

    Result updateComment(int id, CommentUpdateRequest request) throws CommentNotFoundException;

    Result deleteComment(int id) throws CommentNotFoundException;

    void checkIsExistsByCommentId(int id) throws CommentNotFoundException;

    void checkIsExistsByPost(int id) throws PostNotFoundException;

    void checkIfListEmpty(List<?> list) throws NoDataFoundException;

}

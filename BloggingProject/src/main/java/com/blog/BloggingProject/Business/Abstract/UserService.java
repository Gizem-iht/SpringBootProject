package com.blog.BloggingProject.Business.Abstract;

import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dto.UserDto.UserCreateRequest;
import com.blog.BloggingProject.Dto.UserDto.UserListRequest;
import com.blog.BloggingProject.Dto.UserDto.UserUpdateRequest;
import com.blog.BloggingProject.Exception.CategoryException.CategoryAlreadyExistException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.UserException.EmailAlreadyExistException;
import com.blog.BloggingProject.Exception.UserException.UserNotFoundException;

import java.util.List;

public interface UserService {
    DataResult<List<UserListRequest>> getAll() throws NoDataFoundException;

    Result createUser(UserCreateRequest createUser) throws EmailAlreadyExistException;

    Result updateUser(int id, UserUpdateRequest updateUser) throws UserNotFoundException,EmailAlreadyExistException;

    Result deleteUser(int id) throws UserNotFoundException;

    void checkIsExistsByUserId(int id) throws UserNotFoundException;

    void checkIfListEmpty(List<?> list) throws NoDataFoundException;

    void checkIsNotExistByUserEmail(String mail) throws EmailAlreadyExistException;
}

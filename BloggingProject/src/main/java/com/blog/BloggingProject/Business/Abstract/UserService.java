package com.blog.BloggingProject.Business.Abstract;

import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dtos.UserDto.UserListDto;
import com.blog.BloggingProject.Request.UserRequest.UserCreateRequest;
import com.blog.BloggingProject.Request.UserRequest.UserUpdateRequest;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions.EmailAlreadyExistException;
import com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    DataResult<List<UserListDto>> getAll() throws NoDataFoundException;

    Result createUser(UserCreateRequest createUser) throws EmailAlreadyExistException;

    Result updateUser(int id, UserUpdateRequest updateUser) throws UserNotFoundException,EmailAlreadyExistException;

    Result deleteUser(int id) throws UserNotFoundException;

    void checkIsExistsByUserId(int id) throws UserNotFoundException;

    void checkIfListEmpty(List<?> list) throws NoDataFoundException;

    void checkIsNotExistByUserEmail(String mail) throws EmailAlreadyExistException;
}

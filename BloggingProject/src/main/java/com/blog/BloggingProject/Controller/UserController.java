package com.blog.BloggingProject.Controller;

import com.blog.BloggingProject.Business.Abstract.UserService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dtos.UserDto.UserListDto;
import com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions.EmailAlreadyExistException;
import com.blog.BloggingProject.Request.UserRequest.UserCreateRequest;
import com.blog.BloggingProject.Request.UserRequest.UserUpdateRequest;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getall")
    public DataResult<List<UserListDto>> getAll() throws NoDataFoundException {
        return userService.getAll();
    }
    @PostMapping("/add")
    public Result create(@Valid @RequestBody UserCreateRequest request) throws EmailAlreadyExistException {
        return userService.createUser(request);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id,
                         @Valid @RequestBody UserUpdateRequest request)
            throws UserNotFoundException, EmailAlreadyExistException {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id)
            throws UserNotFoundException {
        return userService.deleteUser(id);
    }
}

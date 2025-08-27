package com.blog.BloggingProject.Controller;

import com.blog.BloggingProject.Business.Abstract.UserService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dto.UserDto.UserCreateRequest;
import com.blog.BloggingProject.Dto.UserDto.UserListRequest;
import com.blog.BloggingProject.Dto.UserDto.UserUpdateRequest;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.UserException.UserNotFoundException;
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
    public DataResult<List<UserListRequest>> getAll() throws NoDataFoundException {
        return userService.getAll();
    }
    @PostMapping("/add")
    public Result create(@Valid @RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id,
                         @Valid @RequestBody UserUpdateRequest request)
            throws UserNotFoundException {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id)
            throws UserNotFoundException {
        return userService.deleteUser(id);
    }
}

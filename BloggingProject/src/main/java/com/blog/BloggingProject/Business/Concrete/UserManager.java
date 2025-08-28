package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.UserService;
import com.blog.BloggingProject.Core.Mapping.ModelMapperService.ModelMapperService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dtos.UserDto.UserListDto;
import com.blog.BloggingProject.Entities.Concretes.User;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions.EmailAlreadyExistException;
import com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions.UserNotFoundException;
import com.blog.BloggingProject.Repositories.UserRepository;
import com.blog.BloggingProject.Request.UserRequest.UserCreateRequest;
import com.blog.BloggingProject.Request.UserRequest.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public UserManager(UserRepository userRepository, ModelMapperService modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<UserListDto>> getAll() throws NoDataFoundException {
        List<UserListDto> dtoList = userRepository.findAll()
                .stream()
                .map(user -> this.modelMapperService.forDto().map(user, UserListDto.class))
                .collect(Collectors.toList());

        checkIfListEmpty(dtoList);

        return new SuccessDataResult<>(dtoList, "Users listed successfully.");
    }

    @Override
    public Result createUser(UserCreateRequest req) throws EmailAlreadyExistException {
        checkIsNotExistByUserEmail(req.getMail());

        User user = this.modelMapperService.forRequest().map(req, User.class);
        userRepository.save(user);

        return new SuccessResult("User created successfully.");
    }

    @Override
    public Result updateUser(int id, UserUpdateRequest req) throws UserNotFoundException, EmailAlreadyExistException {
        checkIsExistsByUserId(id);
        checkIsNotExistByUserEmail(req.getMail());

        User existing = userRepository.findById(id).get();

        this.modelMapperService.forRequest().map(req, existing);

        userRepository.save(existing);
        return new SuccessResult("User updated successfully. ID: " + id);
    }

    @Override
    public Result deleteUser(int id) throws UserNotFoundException {
        checkIsExistsByUserId(id);
        userRepository.deleteById(id);
        return new SuccessResult("User deleted successfully. ID: " + id);
    }

    @Override
    public void checkIsExistsByUserId(int userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }

    @Override
    public void checkIfListEmpty(List<?> list) throws NoDataFoundException {
        if (list == null || list.isEmpty()) {
            throw new NoDataFoundException("No data found in the system.");
        }
    }

    @Override
    public void checkIsNotExistByUserEmail(String mail) throws EmailAlreadyExistException {
        if (userRepository.existsByMail(mail)) {
            throw new EmailAlreadyExistException("User already exists with email: " + mail);
        }
    }
}

package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.UserService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dto.UserDto.UserCreateRequest;
import com.blog.BloggingProject.Dto.UserDto.UserListRequest;
import com.blog.BloggingProject.Dto.UserDto.UserUpdateRequest;
import com.blog.BloggingProject.Entities.Concretes.User;
import com.blog.BloggingProject.Exception.CategoryException.CategoryAlreadyExistException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.UserException.EmailAlreadyExistException;
import com.blog.BloggingProject.Exception.UserException.UserNotFoundException;
import com.blog.BloggingProject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DataResult<List<UserListRequest>> getAll() throws NoDataFoundException {
        List<UserListRequest> dtoList = userRepository.findAll()
                .stream()
                .map(u -> new UserListRequest(u.getId(), u.getName(), u.getMail(), u.getPassword()))
                .collect(java.util.stream.Collectors.toList());

        checkIfListEmpty(dtoList);

        return new SuccessDataResult<>(dtoList, "Users listed successfully.");
    }


    @Override
    public Result createUser(UserCreateRequest req) throws EmailAlreadyExistException {

        checkIsNotExistByUserEmail(req.getMail());

        User u = new User();
        u.setName(req.getName());
        u.setMail(req.getMail());
        u.setPassword(req.getPassword());
        userRepository.save(u);

        return new SuccessResult("User created successfully.");
    }

    @Override
    public Result updateUser(int id, UserUpdateRequest req) throws UserNotFoundException {

        checkIsExistsByUserId(id);
        checkIsNotExistByUserEmail(req.getMail());

        User existing = userRepository.findById(id).get();
        existing.setName(req.getName());
        existing.setMail(req.getMail());
        existing.setPassword(req.getPassword());
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
    public void checkIsNotExistByUserEmail(String mail) throws EmailAlreadyExistException{
        if (userRepository.existsByMail(mail)) {
            throw new CategoryAlreadyExistException("User already exists with email: " + mail);
        }
    }
}

package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.PostService;
import com.blog.BloggingProject.Core.Mapping.ModelMapperService.ModelMapperService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dtos.PostDto.PostListDto;
import com.blog.BloggingProject.Entities.Concretes.Category;
import com.blog.BloggingProject.Entities.Concretes.Post;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryNotFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.PostExceptions.PostNotFoundException;
import com.blog.BloggingProject.Repositories.CategoryRepository;
import com.blog.BloggingProject.Repositories.PostRepository;
import com.blog.BloggingProject.Request.PostRequest.PostCreateRequest;
import com.blog.BloggingProject.Request.PostRequest.PostUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostManager implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public PostManager(PostRepository postRepository,
                       CategoryRepository categoryRepository,
                       ModelMapperService modelMapperService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<PostListDto>> getAll() throws NoDataFoundException {
        List<PostListDto> dtoList = postRepository.findAll()
                .stream()
                .map(post -> this.modelMapperService.forDto().map(post, PostListDto.class))
                .collect(Collectors.toList());

        checkIfListEmpty(dtoList);
        return new SuccessDataResult<>(dtoList, "Posts listed successfully.");
    }

    @Override
    public Result createPost(PostCreateRequest postRequest) throws CategoryNotFoundException {

        checkIsExistsByCategoryId(postRequest.getCategoryId());

        Category category = categoryRepository.findById(postRequest.getCategoryId()).get();

        Post post = this.modelMapperService.forRequest().map(postRequest, Post.class);
        post.setCategory(category);
        post.setUploadDate(LocalDate.now());

        postRepository.save(post);
        return new SuccessResult("Post created successfully.");
    }

    @Override
    public Result updatePost(int id, PostUpdateRequest postRequest)
            throws PostNotFoundException, CategoryNotFoundException {

        checkIsExistsByPostId(id);
        checkIsExistsByCategoryId(postRequest.getCategoryId());

        Post existingpost = postRepository.findById(id).get();

        Category category = categoryRepository.findById(postRequest.getCategoryId()).get();

        this.modelMapperService.forRequest().map(postRequest, existingpost);
        existingpost.setCategory(category);
        existingpost.setUploadDate(LocalDate.now());

        postRepository.save(existingpost);
        return new SuccessResult("Post updated successfully. ID: " + id);
    }

    @Override
    public Result deletePost(int id) throws PostNotFoundException {
        checkIsExistsByPostId(id);
        postRepository.deleteById(id);
        return new SuccessResult("Post deleted successfully. ID: " + id);
    }

    @Override
    public void checkIsExistsByPostId(int postId) throws PostNotFoundException {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public void checkIsExistsByCategoryId(int categoryId) throws CategoryNotFoundException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }

    public void checkIfListEmpty(List<?> list) throws NoDataFoundException {
        if (list == null || list.isEmpty()) {
            throw new NoDataFoundException("No data found in the system.");
        }
    }
}

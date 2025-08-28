package com.blog.BloggingProject.Controller;

import com.blog.BloggingProject.Business.Abstract.PostService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dtos.PostDto.PostListDto;
import com.blog.BloggingProject.Request.PostRequest.PostCreateRequest;
import com.blog.BloggingProject.Request.PostRequest.PostUpdateRequest;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryNotFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.PostExceptions.PostNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getall")
    public DataResult<List<PostListDto>> getAll() throws NoDataFoundException {
        return postService.getAll();
    }

    @PostMapping("/add")
    public Result create(@Valid @RequestBody PostCreateRequest request) throws CategoryNotFoundException {
        return postService.createPost(request);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id,  @Valid @RequestBody PostUpdateRequest request) throws PostNotFoundException, CategoryNotFoundException {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) throws PostNotFoundException {
        return postService.deletePost(id);
    }
}

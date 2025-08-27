package com.blog.BloggingProject.Controller;

import com.blog.BloggingProject.Business.Abstract.PostService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dto.PostDto.PostCreateRequest;
import com.blog.BloggingProject.Dto.PostDto.PostListRequest;
import com.blog.BloggingProject.Dto.PostDto.PostUpdateRequest;
import com.blog.BloggingProject.Exception.CategoryException.CategoryNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.PostException.PostNotFoundException;
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
    public DataResult<List<PostListRequest>> getAll() throws NoDataFoundException {
        return postService.getAll();
    }

    @PostMapping("/add")
    public Result create(@Valid @RequestBody PostCreateRequest request)
            throws CategoryNotFoundException {
        return postService.createPost(request);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id,
                         @Valid @RequestBody PostUpdateRequest request)
            throws PostNotFoundException, CategoryNotFoundException {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id)
            throws PostNotFoundException {
        return postService.deletePost(id);
    }
}

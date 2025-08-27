package com.blog.BloggingProject.Controller;

import com.blog.BloggingProject.Business.Abstract.CommentService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dto.CommentDto.CommentCreateRequest;
import com.blog.BloggingProject.Dto.CommentDto.CommentListRequest;
import com.blog.BloggingProject.Dto.CommentDto.CommentUpdateRequest;
import com.blog.BloggingProject.Exception.CommentException.CommentNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.PostException.PostNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getall")
    public DataResult<List<CommentListRequest>> getAll() throws NoDataFoundException {
        return commentService.getAll();
    }

    @PostMapping("/add")
    public Result create(@Valid @RequestBody CommentCreateRequest request) throws PostNotFoundException {
        return commentService.createComment(request);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id,
                         @Valid @RequestBody CommentUpdateRequest request)
            throws CommentNotFoundException, PostNotFoundException {
        return commentService.updateComment(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) throws CommentNotFoundException {
        return commentService.deleteComment(id);
    }
}

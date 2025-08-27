package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.CommentService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dto.CommentDto.CommentCreateRequest;
import com.blog.BloggingProject.Dto.CommentDto.CommentListRequest;
import com.blog.BloggingProject.Dto.CommentDto.CommentUpdateRequest;
import com.blog.BloggingProject.Entities.Concretes.Comment;
import com.blog.BloggingProject.Entities.Concretes.Post;
import com.blog.BloggingProject.Exception.CommentException.CommentNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.PostException.PostNotFoundException;
import com.blog.BloggingProject.Repositories.CommentRepository;
import com.blog.BloggingProject.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentManager implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentManager(CommentRepository commentRepository,
                          PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public DataResult<List<CommentListRequest>> getAll() throws NoDataFoundException {
        List<CommentListRequest> dtoList = commentRepository.findAll()
                .stream()
                .map(co -> new CommentListRequest(
                        co.getId(), co.getFirstName(), co.getLastName(), co.getEmail(), co.getComment()))
                .collect(java.util.stream.Collectors.toList());

        checkIfListEmpty(dtoList);

        return new SuccessDataResult<>(dtoList, "Comments listed successfully.");
    }

    @Override
    public Result createComment(CommentCreateRequest request) throws PostNotFoundException {

        checkIsExistsByPost(request.getPostId());

        Post post = postRepository.findById(request.getPostId()).get();

        Comment c = new Comment();
        c.setPost(post);
        c.setFirstName(request.getFirstName());
        c.setLastName(request.getLastName());
        c.setEmail(request.getEmail());
        c.setComment(request.getComment());

        commentRepository.save(c);

        return new SuccessResult("Comment created successfully.");
    }

    @Override
    public Result updateComment(int id, CommentUpdateRequest request)
            throws CommentNotFoundException{

        checkIsExistsByCommentId(id);

        Comment existing = commentRepository.findById(id).get();

        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setComment(request.getComment());

        commentRepository.save(existing);

        return new SuccessResult("Comment updated successfully. ID: " + id);
    }

    @Override
    public Result deleteComment(int id) throws CommentNotFoundException {

        checkIsExistsByCommentId(id);

        commentRepository.deleteById(id);
        return new SuccessResult("Comment deleted successfully. ID: " + id);
    }

    @Override
    public void checkIsExistsByCommentId(int id) throws CommentNotFoundException {
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("Comment not found with ID: " + id);
        }
    }

    @Override
    public void checkIsExistsByPost(int postId) throws PostNotFoundException {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public void checkIfListEmpty(List<?> list) throws NoDataFoundException {
        if (list == null || list.isEmpty()) {
            throw new NoDataFoundException("No data found in the system.");
        }
    }
}

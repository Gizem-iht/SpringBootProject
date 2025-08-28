package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.CommentService;
import com.blog.BloggingProject.Core.Mapping.ModelMapperService.ModelMapperService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dtos.CommentDto.CommentListDto;
import com.blog.BloggingProject.Entities.Concretes.Comment;
import com.blog.BloggingProject.Entities.Concretes.Post;
import com.blog.BloggingProject.Exceptions.BusinessException.CommentExceptions.CommentNotFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.NoDataFoundException;
import com.blog.BloggingProject.Exceptions.BusinessException.PostExceptions.PostNotFoundException;
import com.blog.BloggingProject.Repositories.CommentRepository;
import com.blog.BloggingProject.Repositories.PostRepository;
import com.blog.BloggingProject.Request.CommentRequest.CommentCreateRequest;
import com.blog.BloggingProject.Request.CommentRequest.CommentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentManager implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CommentManager(CommentRepository commentRepository,
                          PostRepository postRepository,
                          ModelMapperService modelMapperService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CommentListDto>> getAll() throws NoDataFoundException {
        List<CommentListDto> dtoList = commentRepository.findAll()
                .stream()
                .map(comment -> this.modelMapperService.forDto().map(comment, CommentListDto.class))
                .collect(Collectors.toList());

        checkIfListEmpty(dtoList);

        return new SuccessDataResult<>(dtoList, "Comments listed successfully.");

    }

    @Override
    public Result createComment(CommentCreateRequest commentRequest) throws PostNotFoundException {
        checkIsExistsByPost(commentRequest.getPostId());

        Post post = postRepository.findById(commentRequest.getPostId()).get(); //commenta karşılık gelen postu aldık.

        Comment comment = this.modelMapperService.forRequest().map(commentRequest, Comment.class);
        comment.setPost(post);

        commentRepository.save(comment);
        return new SuccessResult("Comment created successfully.");
    }

    @Override
    public Result updateComment(int id, CommentUpdateRequest request)
            throws CommentNotFoundException {
        checkIsExistsByCommentId(id);

        Comment existingcomment = commentRepository.findById(id).get();

        this.modelMapperService.forRequest().map(request, existingcomment);
        //commentUpdateRequest içindeki alanları,veritabanından çekilen existingcomment içine atıyoruz.(Gizem)

        commentRepository.save(existingcomment);
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
        if ( list == null || list.isEmpty()) {
            throw new NoDataFoundException("No data found in the system.");
        }
    }
}

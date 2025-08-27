package com.blog.BloggingProject.Business.Concrete;

import com.blog.BloggingProject.Business.Abstract.PostService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Core.Utilities.SuccessDataResult;
import com.blog.BloggingProject.Core.Utilities.SuccessResult;
import com.blog.BloggingProject.Dto.PostDto.PostCreateRequest;
import com.blog.BloggingProject.Dto.PostDto.PostListRequest;
import com.blog.BloggingProject.Dto.PostDto.PostUpdateRequest;
import com.blog.BloggingProject.Entities.Concretes.Category;
import com.blog.BloggingProject.Entities.Concretes.Post;
import com.blog.BloggingProject.Exception.CategoryException.CategoryNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import com.blog.BloggingProject.Exception.PostException.PostNotFoundException;
import com.blog.BloggingProject.Repositories.CategoryRepository;
import com.blog.BloggingProject.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostManager implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostManager(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public DataResult<List<PostListRequest>> getAll() throws NoDataFoundException {
        List<PostListRequest> dtoList = postRepository.findAll()
                .stream()
                .map(p -> new PostListRequest(p.getId(), p.getTitle(), p.getText(), p.getImage(), p.getUploadDate()))
                .collect(Collectors.toList());

        checkIfListEmpty(dtoList);

        return new SuccessDataResult<>(dtoList, "Posts listed successfully.");
    }


    @Override
    public Result createPost(PostCreateRequest req) throws CategoryNotFoundException {
        checkIsExistsByCategoryId(req.getCategoryId());

        Category cat = categoryRepository.findById(req.getCategoryId()).get();

        Post p = new Post();
        p.setTitle(req.getTitle());
        p.setText(req.getText());
        p.setCategory(cat);
        p.setUploadDate(LocalDate.now());
        p.setImage(req.getImage());

        postRepository.save(p);
        return new SuccessResult("Post created successfully.");
    }

    @Override
    public Result updatePost(int id, PostUpdateRequest req)
            throws PostNotFoundException, CategoryNotFoundException {

        checkIsExistsByPostId(id);
        checkIsExistsByCategoryId(req.getCategoryId());

        Post existing = postRepository.findById(id).get();
        Category cat = categoryRepository.findById(req.getCategoryId()).get();

        existing.setTitle(req.getTitle());
        existing.setText(req.getText());
        existing.setCategory(cat);
        existing.setUploadDate(LocalDate.now());
        existing.setImage(req.getImage());

        postRepository.save(existing);
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

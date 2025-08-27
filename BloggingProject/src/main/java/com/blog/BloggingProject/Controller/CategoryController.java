package com.blog.BloggingProject.Controller;

import com.blog.BloggingProject.Business.Abstract.CategoryService;
import com.blog.BloggingProject.Core.Utilities.DataResult;
import com.blog.BloggingProject.Core.Utilities.Result;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryCreateRequest;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryListRequest;
import com.blog.BloggingProject.Dto.CategoryDto.CategoryUpdateRequest;
import com.blog.BloggingProject.Exception.CategoryException.CategoryNotFoundException;
import com.blog.BloggingProject.Exception.NoDataFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService ;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public DataResult<List<CategoryListRequest>> getAll() throws NoDataFoundException {
        return categoryService.getAll();
    }


    @PostMapping("/add")
    public Result create(@Valid @RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @Valid @RequestBody CategoryUpdateRequest request)
            throws CategoryNotFoundException {
        request.setCategoryId(id);
        return categoryService.updateCategory(request);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) throws CategoryNotFoundException {
        return categoryService.deleteCategory(id);
    }
}

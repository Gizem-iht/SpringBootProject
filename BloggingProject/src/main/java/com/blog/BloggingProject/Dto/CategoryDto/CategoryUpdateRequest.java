package com.blog.BloggingProject.Dto.CategoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryUpdateRequest {

    private int categoryId;
    @NotBlank(message="Name cannot be blank")
    private String name;
}

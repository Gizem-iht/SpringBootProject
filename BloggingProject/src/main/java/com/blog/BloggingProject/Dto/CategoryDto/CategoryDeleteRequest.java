package com.blog.BloggingProject.Dto.CategoryDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDeleteRequest {

    @NotNull
    private int id;
}

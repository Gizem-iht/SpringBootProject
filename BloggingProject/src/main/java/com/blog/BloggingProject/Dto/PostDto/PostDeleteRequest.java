package com.blog.BloggingProject.Dto.PostDto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDeleteRequest {

    @NotNull
    private int id;
}

package com.blog.BloggingProject.Dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

    private String title;
    private int categoryId;
    private String text;
    private String image;
}

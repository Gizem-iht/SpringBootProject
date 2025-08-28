package com.blog.BloggingProject.Request.PostRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

    private String title;
    private int categoryId;
    private String text;
    private String image;
    private LocalDate uploadDate;
}

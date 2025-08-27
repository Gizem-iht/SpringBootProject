package com.blog.BloggingProject.Dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostListRequest {

    private int id;
    private String title;
    private String text;
    private String image;
    private LocalDate uploadDate;
}

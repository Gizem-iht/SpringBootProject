package com.blog.BloggingProject.Dtos.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListDto {

    private int id;
    private String title;
    private String text;
    private String image;
    private LocalDate uploadDate;

}

package com.blog.BloggingProject.Dtos.CommentDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentListDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String comment;


}

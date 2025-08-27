package com.blog.BloggingProject.Dto.CommentDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListRequest {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String comment;
}

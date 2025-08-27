package com.blog.BloggingProject.Dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private int postId;
    private String comment;

}
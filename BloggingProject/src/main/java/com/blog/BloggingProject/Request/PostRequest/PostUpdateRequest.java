package com.blog.BloggingProject.Request.PostRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Category id cannot be blank")
    private int categoryId;

    @NotBlank(message = "Text cannot be blank")
    private String text;

    @NotBlank(message = "Image cannot be blank")
    private String image;

    @NotBlank(message= "Upload date cannot be blank")
    private LocalDate upload_date;
}
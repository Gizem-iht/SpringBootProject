package com.blog.BloggingProject.Dto.UserDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Mail cannot be blank")
    private String mail;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}

package com.blog.BloggingProject.Dtos.UserDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDto {

    private int id;
    private String name;
    private String email;
    private String password;

}

package com.blog.BloggingProject.Dto.UserDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListRequest {

    private int id;
    private String name;
    private String email;
    private String passwordd;

}

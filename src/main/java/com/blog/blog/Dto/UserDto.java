package com.blog.blog.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class UserDto {
    private int id;

    private String name;

    private String email;

    private String password;



}
package com.blog.blog.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String content;
    private Date addedDate;
    private UserDto user;
}

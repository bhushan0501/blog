package com.blog.blog.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Entity
@Table(name="Post")
@Setter
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name= "Post Title")
    private String title;
    private String content;
//    private Date addedDate;

    @ManyToOne
    private User user;
    public Integer getId() {
        return postId;
    }
}

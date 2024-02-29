package com.social.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caption;
    private String image;
    private String video;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime createdAt;

    @ManyToMany
    private List<User> liked = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}

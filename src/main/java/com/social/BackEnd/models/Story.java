package com.social.BackEnd.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;
    private String video;

    private LocalDateTime createdAt =  LocalDateTime.now();

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private List<User> likes = new ArrayList<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    private List<User> watchedBy = new ArrayList<>();
}

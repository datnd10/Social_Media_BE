package com.social.BackEnd.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Nontification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Post post;

    private Boolean isRead = false;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;
}

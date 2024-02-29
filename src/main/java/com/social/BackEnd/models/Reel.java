package com.social.BackEnd.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String video;

    private LocalDateTime createdAt =  LocalDateTime.now();

    @ManyToOne
    private User user;


}

package com.social.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column (name = "first_name")
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    private String avatar;
    private String bio;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();

    @ManyToMany
    private List<Post> savedPosts = new ArrayList<>();



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", followers=" + followers +
                ", followings=" + followings +
                ", savedPosts=" + savedPosts +
                '}';
    }
}

package com.social.BackEnd.service.implementation;

import com.social.BackEnd.exception.UserException;
import com.social.BackEnd.models.Post;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.PostRespository;
import com.social.BackEnd.repository.UserRepository;
import com.social.BackEnd.service.PostService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    PostRespository postRespository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post newPost = new Post();

        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        newPost.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRespository.save(newPost);
        return savedPost;
    }

    @Override
    public Post deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (user.getId() != post.getUser().getId()) {
            throw new Exception("You can delete another post");
        }
        post.setDeleted(true);
        return postRespository.save(post);
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return postRespository.findPostByUserId(userId, sortByCreatedAtDesc);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> otp = postRespository.findById(postId);
        if (otp.isEmpty()) {
            throw new Exception("post not found with id " + postId);
        }
        return otp.get();
    }

    @Override
    public List<Post> findAllPosts() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return postRespository.getAllByPostAvailable(sortByCreatedAtDesc);
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getSavedBy().contains(user)) {
            post.getSavedBy().remove(user);
        } else {
            post.getSavedBy().add(user);
        }
        postRespository.save(post);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }
        return postRespository.save(post);
    }

    @Override
    public List<Post> findBySavedBy(Integer userId) throws UserException {
        User user = userService.findUserById(userId);
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return postRespository.findBySavedBy(user, sortByCreatedAtDesc);
    }

    @Override
    public Post updatePost(Integer postId, Post post) throws Exception {
        Post post1 = findPostById(postId);
        post1.setCaption(post.getCaption());
        post1.setImage(post.getImage());
        post1.setVideo(post.getVideo());
        return postRespository.save(post1);
    }
}

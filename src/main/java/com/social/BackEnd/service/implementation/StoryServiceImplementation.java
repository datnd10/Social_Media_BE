package com.social.BackEnd.service.implementation;

import com.social.BackEnd.models.Post;
import com.social.BackEnd.models.Story;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.StoryRepository;
import com.social.BackEnd.service.StoryService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Story createStory(Story story, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Story newStory = new Story();

        newStory.setUser(user);
        newStory.setImage(story.getImage());
        newStory.setVideo(story.getVideo());

        return storyRepository.save(newStory);
    }

    @Override
    public String deleteStory(Integer storyId, Integer userId) throws Exception {
        Story story = findStoryById(storyId);
        User user = userService.findUserById(userId);
        if (user.getId() != story.getUser().getId()) {
            throw new Exception("You can delete another post");
        }
        storyRepository.delete(story);
        return "post deleted successfully";
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) {
        return storyRepository.findStoryByUserId(userId);
    }

    @Override
    public Story findStoryById(Integer storyId) throws Exception {
        Optional<Story> story = storyRepository.findById(storyId);
        if (story.isEmpty()) {
            throw new Exception("story not found with id " + storyId);
        }
        return story.get();
    }

    @Override
    public List<Story> findAllStoryAvailable() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime oneDayAgo = currentDate.minusDays(1);
        return storyRepository.findAll();
    }

    @Override
    public List<Story> findByFollowings(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        List<Integer> followings = user.getFollowings();

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime oneDayAgo = currentDate.minusDays(1);
        List<Story> story = storyRepository.findStoriesByUser(userId, oneDayAgo);
        List<Story> stories = storyRepository.findByUserInAndCreatedAtAfterOrUserIdOrderByCreatedAtDesc(followings, oneDayAgo);

        story.addAll(stories);

        return story;
    }

    @Override
    public Story reactStory(Integer storyId, Integer userId) throws Exception {
        Story story = findStoryById(storyId);
        User user = userService.findUserById(userId);
        if (story.getLikes().contains(user)) {
            story.getLikes().remove(user);
        } else {
            story.getLikes().add(user);
        }
        return storyRepository.save(story);
    }

    @Override
    public Story watchStory(Integer storyId, Integer userId) throws Exception {
        Story story = findStoryById(storyId);
        User user = userService.findUserById(userId);
        if (!story.getWatchedBy().contains(user)) {
            story.getWatchedBy().add(user);
        }
        return storyRepository.save(story);
    }
}

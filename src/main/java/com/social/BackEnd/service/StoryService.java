package com.social.BackEnd.service;

import com.social.BackEnd.models.Story;

import java.util.List;

public interface StoryService {

    Story createStory(Story story, Integer userId) throws Exception;
    String deleteStory(Integer storyId, Integer userId) throws Exception;
    List<Story> findStoryByUserId(Integer userId);
    Story findStoryById(Integer storyId) throws Exception;
    List<Story> findAllStoryAvailable();

    List<Story> findByFollowings(Integer userId) throws Exception;

    Story reactStory(Integer storyId, Integer userId) throws Exception;

    Story watchStory(Integer storyId, Integer userId) throws Exception;
}

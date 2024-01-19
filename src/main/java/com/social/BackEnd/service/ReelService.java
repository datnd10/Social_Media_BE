package com.social.BackEnd.service;

import com.social.BackEnd.models.Reel;
import com.social.BackEnd.models.User;

import java.util.List;

public interface ReelService {

    public Reel createReel(Reel reel, User user);

    public List<Reel> findAllReels();

    public List<Reel> findUserReels(Integer userId) throws Exception;


}

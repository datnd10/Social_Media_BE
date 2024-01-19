package com.social.BackEnd.service.implementation;

import com.social.BackEnd.models.Reel;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.ReelRepository;
import com.social.BackEnd.service.ReelService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelServiceImplementation implements ReelService {

    @Autowired
    private ReelRepository reelRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reel createReel(Reel reel, User user) {
        Reel reelCreated = new Reel();
        reelCreated.setTitle(reel.getTitle());
        reelCreated.setVideo(reel.getVideo());
        reelCreated.setUser(user);
        return reelRepository.save(reelCreated);
    }

    @Override
    public List<Reel> findAllReels() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> findUserReels(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelRepository.findByUserId(userId);
    }
}

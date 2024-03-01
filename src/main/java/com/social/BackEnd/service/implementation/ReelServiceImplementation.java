package com.social.BackEnd.service.implementation;

import com.social.BackEnd.models.Post;
import com.social.BackEnd.models.Reel;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.ReelRepository;
import com.social.BackEnd.service.ReelService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return reelRepository.findAll(sortByCreatedAtDesc);
    }

    @Override
    public List<Reel> findUserReels(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelRepository.findByUserId(userId);
    }

    @Override
    public String deleteReel(Long reelId) throws Exception {
        Reel reel = reelRepository.findById(reelId).get();
        if (reel == null) {
            throw new Exception("reel not found with id " + reelId);
        }
        reelRepository.delete(reel);
        return "reel deleted successfully";
    }
}

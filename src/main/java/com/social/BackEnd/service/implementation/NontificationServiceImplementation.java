package com.social.BackEnd.service.implementation;

import com.social.BackEnd.exception.UserException;
import com.social.BackEnd.models.Nontification;
import com.social.BackEnd.models.Post;
import com.social.BackEnd.models.Story;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.NontificationRepository;
import com.social.BackEnd.service.NontificationService;
import com.social.BackEnd.service.PostService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NontificationServiceImplementation implements NontificationService {
    @Autowired
    private NontificationRepository nontificationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;
    @Override
    public Nontification createNontification(Nontification nontification,Integer toUserId, Integer fromUserId, Integer postId) throws Exception {

        User toUser = userService.findUserById(toUserId);
        User fromUser = userService.findUserById(fromUserId);
        Post post = postService.findPostById(postId);

        Nontification newNontification = new Nontification();

        newNontification.setPost(post);
        newNontification.setFromUser(fromUser);
        newNontification.setToUser(toUser);
        newNontification.setContent(nontification.getContent());

        return nontificationRepository.save(newNontification);
    }

    @Override
    public Nontification findNontificationById(Integer nontificationId) {
        Optional<Nontification> nontification = nontificationRepository.findById(nontificationId);
        if (nontification.isEmpty()) {
            return null;
        }
        return nontification.get();
    }

    @Override
    public List<Nontification> findNontificationByUserId(Integer userId) {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return nontificationRepository.findNontificationByToUserId(userId, sortByCreatedAtDesc);
    }

    @Override
    public String deleteNontification(Integer nontificationId) throws Exception {
        Nontification nontification = findNontificationById(nontificationId);
        if (nontification == null) {
            throw new UserException("nontification not found with id " + nontificationId);
        }
        nontificationRepository.delete(nontification);
        return "nontification deleted successfully";
    }

    @Override
    public Nontification watchNontification(Integer nontificationId) throws Exception {
        Nontification notification = findNontificationById(nontificationId);

        if (notification == null) {
            throw new UserException("Notification not found with id " + nontificationId);
        }
        notification.setIsRead(true); // Assuming you meant to set 'seen' to true

        return nontificationRepository.save(notification);
    }

}

package com.social.BackEnd.service;

import com.social.BackEnd.models.Nontification;
import com.social.BackEnd.models.Story;

import java.util.List;

public interface NontificationService {
    
    Nontification createNontification(Nontification nontification, Integer toUserId, Integer fromUserId, Integer postId) throws Exception;

    Nontification findNontificationById(Integer nontificationId);
    
    List<Nontification> findNontificationByUserId(Integer userId);

    String deleteNontification(Integer nontificationId) throws Exception;

    Nontification watchNontification(Integer nontificationId) throws Exception;
}

package com.social.BackEnd.service;

import com.social.BackEnd.models.Notification;

import java.util.List;

public interface NontificationService {
    
    Notification createNontification(Notification notification, Integer toUserId, Integer fromUserId, Integer postId) throws Exception;

    Notification findNontificationById(Integer nontificationId);
    
    List<Notification> findNontificationByUserId(Integer userId);

    String deleteNontification(Integer nontificationId) throws Exception;

    Notification watchNontification(Integer nontificationId) throws Exception;
}

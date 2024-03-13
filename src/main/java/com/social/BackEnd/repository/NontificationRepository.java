package com.social.BackEnd.repository;

import com.social.BackEnd.models.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NontificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findNontificationByToUserId(Integer userId, Sort sort);
}

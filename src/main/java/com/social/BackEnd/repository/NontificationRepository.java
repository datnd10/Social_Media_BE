package com.social.BackEnd.repository;

import com.social.BackEnd.models.Nontification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NontificationRepository extends JpaRepository<Nontification, Integer> {

    List<Nontification> findNontificationByToUserId(Integer userId, Sort sort);
}

package com.social.BackEnd.repository;

import com.social.BackEnd.models.Reel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelRepository extends JpaRepository<Reel, Long> {

    public List<Reel> findByUserId(Integer userId);
}

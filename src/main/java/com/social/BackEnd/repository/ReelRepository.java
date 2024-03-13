package com.social.BackEnd.repository;

import com.social.BackEnd.models.Reel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReelRepository extends JpaRepository<Reel, Long> {

    @Query("select r from Reel r where r.user.id = :userId and r.isDeleted = false")
    public List<Reel> findByUserIdAvailable(Integer userId, Sort sortByCreatedAtDesc);

    @Query("select r from Reel r where r.isDeleted = false")
    public List<Reel> findAllAvailableReels(Sort sortByCreatedAtDesc);
}

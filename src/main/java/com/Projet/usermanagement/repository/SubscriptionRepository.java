package com.Projet.usermanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Projet.base.repository.BaseRepository;
import com.Projet.usermanagement.entity.Subscription;

@Repository
public interface SubscriptionRepository extends BaseRepository <Subscription, Long>{
	List<Subscription> findByEndDateLessThanAndStatus(LocalDate currentDate, String statut);
    List<Subscription> findByStatus(String status);

}

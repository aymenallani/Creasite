package com.Projet.usermanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Projet.base.service.BaseService;
import com.Projet.usermanagement.entity.AppUser;
import com.Projet.usermanagement.entity.Subscription;
import com.Projet.usermanagement.repository.SubscriptionRepository;
import com.Projet.usermanagement.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class SubscriptionService extends BaseService<Subscription, Long>{
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Subscription createSubscription(Subscription newsub) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }

        // Get the authenticated user's username and retrieve the user from the database
        String username = authentication.getName();
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
		Subscription sub = new Subscription();
		sub.setStartDate(LocalDate.now());
		sub.setStatus("ACTIVE");
		sub.setPaymentMethod(newsub.getPaymentMethod());
        sub.setEndDate(sub.getStartDate().plusMonths(1));
        sub.setType(newsub.getType());
        if (newsub.getType().equals("Gratuit")) {
        		sub.setAmountPaid(0);
        } else if (newsub.getType().equals("Basique")){
    		sub.setAmountPaid(25);
        } else if (newsub.getType().equals("Professionnel")){
    		sub.setAmountPaid(100);
        }
        sub.setUser(user);
        return subscriptionRepository.save(sub);

	}
	
	@Transactional
	public void updateSubscriptionStatus() {
        LocalDate currentDate = LocalDate.now();
        List<Subscription> subscriptions = subscriptionRepository.findByEndDateLessThanAndStatus(currentDate, "active");
        for (Subscription subscription : subscriptions) {
            subscription.setStatus("inactive");
            subscriptionRepository.save(subscription);
        }
    }
	
	public List<Subscription> findActiveSubscriptions() {
        return subscriptionRepository.findByStatus("ACTIVE");
    }
	
	@Transactional
    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

}

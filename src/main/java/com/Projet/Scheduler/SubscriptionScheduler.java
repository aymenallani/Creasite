package com.Projet.Scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.Projet.usermanagement.entity.Subscription;
import com.Projet.usermanagement.service.SubscriptionService;

@Component
public class SubscriptionScheduler {

    @Autowired
    private SubscriptionService subscriptionService;

    @Scheduled(cron = "0 0 0 * * *") // run every day at midnight
    public void updateSubscriptionStatus() {
        List<Subscription> subscriptions = subscriptionService.findActiveSubscriptions();
        LocalDate currentDate = LocalDate.now();
        for (Subscription subscription : subscriptions) {
            if (subscription.getEndDate().isBefore(currentDate)) {
                subscription.setStatus("INACTIVE");
                subscriptionService.saveSubscription(subscription);
            }
        }
    }
}

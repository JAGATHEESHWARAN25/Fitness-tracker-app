package com.examly.springapp.service;

import com.examly.springapp.model.UserPlanProgress;
import com.examly.springapp.repository.UserPlanProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPlanProgressService {
    @Autowired
    private UserPlanProgressRepository progressRepository;

    public UserPlanProgress assignPlan(UserPlanProgress progress) {
        progress.setCompletionPercentage(0);
        return progressRepository.save(progress);
    }

    public List<UserPlanProgress> getAllProgress() {
        return progressRepository.findAll();
    }

    public Optional<UserPlanProgress> getProgressById(Long id) {
        return progressRepository.findById(id);
    }

    public UserPlanProgress updateProgress(Long id, UserPlanProgress updated) {
        return progressRepository.findById(id).map(p -> {
            p.setCompletionPercentage(updated.getCompletionPercentage());
            p.setLastUpdated(updated.getLastUpdated());
            return progressRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Progress not found"));
    }

    public void deleteProgress(Long id) {
        progressRepository.deleteById(id);
    }
}

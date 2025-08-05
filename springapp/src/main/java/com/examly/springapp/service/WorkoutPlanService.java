package com.examly.springapp.service;

import com.examly.springapp.model.WorkoutPlan;
import com.examly.springapp.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanService {
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlan createPlan(WorkoutPlan plan) {
        return workoutPlanRepository.save(plan);
    }

    public List<WorkoutPlan> getAllPlans() {
        return workoutPlanRepository.findAll();
    }

    public Optional<WorkoutPlan> getPlanById(Long id) {
        return workoutPlanRepository.findById(id);
    }

    public WorkoutPlan updatePlan(Long id, WorkoutPlan updated) {
        return workoutPlanRepository.findById(id).map(plan -> {
            plan.setTitle(updated.getTitle());
            plan.setDescription(updated.getDescription());
            plan.setDifficulty(updated.getDifficulty());
            return workoutPlanRepository.save(plan);
        }).orElseThrow(() -> new RuntimeException("Workout plan not found"));
    }

    public void deletePlan(Long id) {
        workoutPlanRepository.deleteById(id);
    }
}

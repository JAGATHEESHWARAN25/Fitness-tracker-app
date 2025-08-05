package com.examly.springapp.controller;

import com.examly.springapp.model.UserPlanProgress;
import com.examly.springapp.service.UserPlanProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-progress")
public class UserPlanProgressController {

    @Autowired
    private UserPlanProgressService progressService;

    // ➤ Assign a workout plan to a user (initial completion: 0%)
    @PostMapping
    public ResponseEntity<UserPlanProgress> assignPlan(@RequestBody UserPlanProgress progress) {
        UserPlanProgress created = progressService.assignPlan(progress);
        return ResponseEntity.status(201).body(created);
    }

    // ➤ Get all user progress records
    @GetMapping
    public List<UserPlanProgress> getAllProgress() {
        return progressService.getAllProgress();
    }

    // ➤ Get progress by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserPlanProgress> getProgressById(@PathVariable Long id) {
        return progressService.getProgressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ➤ Update progress record
    @PutMapping("/{id}")
    public ResponseEntity<UserPlanProgress> updateProgress(@PathVariable Long id, @RequestBody UserPlanProgress updated) {
        try {
            UserPlanProgress result = progressService.updateProgress(id, updated);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ➤ Delete progress record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}

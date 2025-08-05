package com.examly.springapp.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Date joinDate;

    @Column(nullable = false, updatable = false)
    private Date createdDate = new Date();

    private Date lastLogin;

    private Boolean isActive = true;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<WorkoutPlan> createdWorkoutPlans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlanProgress> progressList;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<ExerciseLibrary> createdExercises;

    public User() {}

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<WorkoutPlan> getCreatedWorkoutPlans() {
        return createdWorkoutPlans;
    }

    public void setCreatedWorkoutPlans(List<WorkoutPlan> createdWorkoutPlans) {
        this.createdWorkoutPlans = createdWorkoutPlans;
    }

    public List<UserPlanProgress> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<UserPlanProgress> progressList) {
        this.progressList = progressList;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<ExerciseLibrary> getCreatedExercises() {
        return createdExercises;
    }

    public void setCreatedExercises(List<ExerciseLibrary> createdExercises) {
        this.createdExercises = createdExercises;
    }
}

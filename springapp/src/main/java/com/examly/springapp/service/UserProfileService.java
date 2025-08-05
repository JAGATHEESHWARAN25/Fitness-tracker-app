package com.examly.springapp.service;

import com.examly.springapp.model.User;
import com.examly.springapp.model.UserProfile;
import com.examly.springapp.repository.UserProfileRepository;
import com.examly.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserProfileService(UserProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    // Create profile for a user
    public UserProfile createProfile(UserProfile profile, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            profile.setUser(userOpt.get());
            return profileRepository.save(profile);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    // Get all user profiles
    public List<UserProfile> getAllProfiles() {
        return profileRepository.findAll();
    }

    // Get profile by profile ID
    public Optional<UserProfile> getProfileById(Long profileId) {
        return profileRepository.findById(profileId);
    }

    // Get profile by user ID
    public Optional<UserProfile> getProfileByUserId(Long userId) {
        return profileRepository.findById(userId);
    }

    // Update profile
    public UserProfile updateProfile(Long profileId, UserProfile updated) {
        return profileRepository.findById(profileId).map(existing -> {
            existing.setAge(updated.getAge());
            existing.setHeight(updated.getHeight());
            existing.setWeight(updated.getWeight());
            existing.setGender(updated.getGender());
            existing.setFitnessGoal(updated.getFitnessGoal());
            existing.setActivityLevel(updated.getActivityLevel());
            return profileRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Profile not found with ID: " + profileId));
    }

    // Delete profile
    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }
}

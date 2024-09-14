package com.nilotpal.collegedirectory.controller;

import com.nilotpal.collegedirectory.model.AdministratorProfile;

import com.nilotpal.collegedirectory.service.AdministratorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminProfiles")
public class AdminController {
    @Autowired
    private AdministratorProfileService administratorProfileService;

    @GetMapping("/{userId}")
    public ResponseEntity<AdministratorProfile> getAdministratorProfile(@PathVariable Long userId) {
        AdministratorProfile profile = administratorProfileService.getAdministratorProfile(userId);
        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<AdministratorProfile> createAdministratorProfile(@RequestBody AdministratorProfile administratorProfile) {
        AdministratorProfile savedProfile = administratorProfileService.saveAdministratorProfile(administratorProfile);
        return ResponseEntity.ok(savedProfile);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AdministratorProfile> updateAdministratorProfile(@PathVariable Long userId, @RequestBody AdministratorProfile administratorProfileDetails) {
        AdministratorProfile existingProfile = administratorProfileService.getAdministratorProfile(userId);
        existingProfile.setPhoto(administratorProfileDetails.getPhoto());
        existingProfile.setDepartment(administratorProfileDetails.getDepartment());
        AdministratorProfile updatedProfile = administratorProfileService.saveAdministratorProfile(existingProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteAdministratorProfile(@PathVariable Long userId) {
        administratorProfileService.deleteAdministratorProfile(userId);
        return ResponseEntity.noContent().build();
    }
}
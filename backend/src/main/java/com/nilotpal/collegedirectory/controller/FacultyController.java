package com.nilotpal.collegedirectory.controller;

import com.nilotpal.collegedirectory.exception.CollegeDirectoryException;
import com.nilotpal.collegedirectory.model.FacultyProfile;
import com.nilotpal.collegedirectory.request.FacultyProfileRequest;
import com.nilotpal.collegedirectory.response.BaseResponse;
import com.nilotpal.collegedirectory.service.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {
    @Autowired
    private FacultyProfileService facultyProfileService;

    @GetMapping("/{userId}")
    public ResponseEntity<FacultyProfile> getFacultyProfile(@PathVariable Long userId) {
        FacultyProfile profile = facultyProfileService.getFacultyProfile(userId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<FacultyProfile>> getAllFacultyProfiles() {
        List<FacultyProfile> profiles = facultyProfileService.getAllFacultyProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createFacultyProfile(@RequestBody FacultyProfileRequest request) {
        try {
            return ResponseEntity.ok(facultyProfileService.saveFacultyProfile(request));
        } catch (CollegeDirectoryException e) {
            return ResponseEntity.badRequest().body(BaseResponse.builder().message("Failed to save").build());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<BaseResponse> updateFacultyProfile(@PathVariable Long userId, @RequestBody FacultyProfileRequest request) {
        try {
            return ResponseEntity.ok(facultyProfileService.updateFacultyProfile(userId, request));
        } catch (CollegeDirectoryException e) {
            return ResponseEntity.badRequest().body(BaseResponse.builder().message("Failed to save").build());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<BaseResponse> deleteFacultyProfile(@PathVariable Long userId) {
        facultyProfileService.deleteFacultyProfile(userId);
        return ResponseEntity.ok(BaseResponse.builder().message("Deleted successfully").build());
    }
}

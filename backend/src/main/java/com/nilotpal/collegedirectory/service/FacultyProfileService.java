package com.nilotpal.collegedirectory.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.nilotpal.collegedirectory.dto.StudentInfoDTO;
import com.nilotpal.collegedirectory.exception.CollegeDirectoryException;
import com.nilotpal.collegedirectory.model.Department;
import com.nilotpal.collegedirectory.model.FacultyProfile;
import com.nilotpal.collegedirectory.model.User;
import com.nilotpal.collegedirectory.repository.DepartmentRepository;
import com.nilotpal.collegedirectory.repository.FacultyProfileRepository;
import com.nilotpal.collegedirectory.repository.UserRepository;
import com.nilotpal.collegedirectory.request.FacultyProfileRequest;
import com.nilotpal.collegedirectory.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyProfileService {
    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    public FacultyProfile getFacultyProfile(Long userId) {
        return facultyProfileRepository.findByUserId(String.valueOf(userId));
    }

    public BaseResponse saveFacultyProfile(FacultyProfileRequest request) throws CollegeDirectoryException {
        FacultyProfile facultyProfile = new FacultyProfile();
        facultyProfile.setPhoto(request.getPhotoUrl());
        facultyProfile.setOfficeHours(request.getOfficeHours());
        Optional<Department> department = departmentRepository.findById(request.getDepartmentId());
        if (department.isPresent()) {
            facultyProfile.setDepartment(department.get());
        } else {
            throw new CollegeDirectoryException("Invalid dept id");

        }
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isPresent()) {
            facultyProfile.setUser(user.get());
        } else {
            throw new CollegeDirectoryException("Invalid user id");
        }
        facultyProfileRepository.save(facultyProfile);
        return BaseResponse.builder().message("Saved successfully").build();
    }

    public void deleteFacultyProfile(Long userId) {
        facultyProfileRepository.delete(String.valueOf(userId));
    }

    public List<FacultyProfile> getAllFacultyProfiles() {
        return facultyProfileRepository.findAll();
    }

    public List<StudentInfoDTO> getAllStudentsUnderFaculty(String facultyId) {
        return facultyProfileRepository.findStudentsByFacultyDepartment(facultyId);
    }

    public BaseResponse updateFacultyProfile(Long userId, FacultyProfileRequest request) throws CollegeDirectoryException {
        request.setUserId(userId);
        return saveFacultyProfile(request);
    }
}
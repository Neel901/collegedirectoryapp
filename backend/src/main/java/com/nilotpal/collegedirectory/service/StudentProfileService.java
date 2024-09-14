package com.nilotpal.collegedirectory.service;

import com.nilotpal.collegedirectory.exception.CollegeDirectoryException;
import com.nilotpal.collegedirectory.model.Department;
import com.nilotpal.collegedirectory.model.StudentProfile;
import com.nilotpal.collegedirectory.model.User;
import com.nilotpal.collegedirectory.repository.DepartmentRepository;
import com.nilotpal.collegedirectory.repository.StudentProfileRepository;
import com.nilotpal.collegedirectory.repository.UserRepository;
import com.nilotpal.collegedirectory.request.StudentProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {
    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public StudentProfile getStudentProfile(Long userId) {
        return studentProfileRepository.findById(userId).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public StudentProfile saveStudentProfile(StudentProfileRequest request) throws CollegeDirectoryException {
        StudentProfile studentProfile = new StudentProfile();
        Optional<User> userOptional = userRepository.findById(request.getStudentId());
        if (userOptional.isPresent())
            studentProfile.setUser(userOptional.get());
        else
            throw new CollegeDirectoryException("Invalid id");
        Optional<Department> department = departmentRepository.findById(request.getDepartmentId());
        if (department.isPresent()) {
            studentProfile.setDepartment(department.get());
        }
        else
            throw new CollegeDirectoryException("Invalid department id");
        studentProfile.setPhoto(request.getPhotoUrl());
        studentProfile.setYear(request.getYear());
        return studentProfileRepository.save(studentProfile);
    }

    public void deleteStudentProfile(Long userId) {
        studentProfileRepository.deleteById(userId);
    }

    public List<StudentProfile> searchByName(String name) {
        return studentProfileRepository.findByUsername(name);
    }

    public List<StudentProfile> searchByDepartment(String department) {
        return studentProfileRepository.findByDepartmentName(department);
    }

    public List<StudentProfile> searchByYear(String year) {
        return studentProfileRepository.findByYear(year);

    }
}
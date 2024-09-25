package com.nilotpal.collegedirectory.controller;

import com.nilotpal.collegedirectory.exception.CollegeDirectoryException;
import com.nilotpal.collegedirectory.model.StudentProfile;
import com.nilotpal.collegedirectory.request.StudentProfileRequest;
import com.nilotpal.collegedirectory.response.BaseResponse;
import com.nilotpal.collegedirectory.response.StudentProfileResponse;
import com.nilotpal.collegedirectory.service.StudentProfileService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping("/{userId}")
    public List<StudentProfile> getStudentProfile(@PathVariable Long userId) {
        return studentProfileService.getStudentProfile(userId);
    }

    @GetMapping("/search")
    public List<StudentProfile> searchStudents(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) String department,
                                               @RequestParam(required = false) String year) {
       if (StringUtils.isNotBlank(name)) {
           return studentProfileService.searchByName(name);
       }
        if (StringUtils.isNotBlank(department)) {
            return studentProfileService.searchByDepartment(department);
        }
        if (StringUtils.isNotBlank(year)) {
            return studentProfileService.searchByYear(year);
        }
        throw new RuntimeException("One must be present");
    }

    @PostMapping
    public ResponseEntity<StudentProfileResponse> saveStudentProfile(@RequestBody StudentProfileRequest studentProfile) {
        try {
            return ResponseEntity.ok(StudentProfileResponse.builder().studentProfile(
                    studentProfileService.saveStudentProfile(studentProfile)).build());
        } catch (CollegeDirectoryException e) {
            return ResponseEntity.badRequest().body(StudentProfileResponse.builder().message("Bad Request").build());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<BaseResponse> deleteStudentProfile(@PathVariable Long userId) {
        studentProfileService.deleteStudentProfile(userId);
        return ResponseEntity.ok(BaseResponse.builder().message("Deleted successfully").build());
    }
}

package com.nilotpal.collegedirectory.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyProfileRequest {
    private Long userId;
    private String photoUrl;
    private String officeHours;
    private Long departmentId;
}

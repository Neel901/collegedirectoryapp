package com.nilotpal.collegedirectory.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileRequest {
    private Long studentId;
    private String photoUrl;
    private String year;
    private Long departmentId;
}

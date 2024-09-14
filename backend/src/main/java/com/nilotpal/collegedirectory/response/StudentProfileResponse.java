package com.nilotpal.collegedirectory.response;

import com.nilotpal.collegedirectory.model.StudentProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentProfileResponse {

    String message;
    StudentProfile studentProfile;
}

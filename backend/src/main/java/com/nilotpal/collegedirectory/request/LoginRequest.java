package com.nilotpal.collegedirectory.request;

import com.nilotpal.collegedirectory.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class is used for handling login requests
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
    private Role role;
}

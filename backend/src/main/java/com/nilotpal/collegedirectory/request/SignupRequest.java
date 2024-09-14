package com.nilotpal.collegedirectory.request;

import com.nilotpal.collegedirectory.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String username;
    private String email;
    private String phone;
    private String password;
    private Role role;
}

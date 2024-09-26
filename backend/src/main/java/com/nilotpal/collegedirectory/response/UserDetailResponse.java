package com.nilotpal.collegedirectory.response;

import com.nilotpal.collegedirectory.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailResponse {

    private Long id;
    private String username;
    private Role role;
    private String name;
    private String email;
    private String phone;

}

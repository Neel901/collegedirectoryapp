package com.nilotpal.collegedirectory.repository;

import com.nilotpal.collegedirectory.constant.Role;
import com.nilotpal.collegedirectory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPasswordAndRole(String username,String password, Role role);
}

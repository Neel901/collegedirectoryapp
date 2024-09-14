package com.nilotpal.collegedirectory.service;

import com.nilotpal.collegedirectory.model.AdministratorProfile;
import com.nilotpal.collegedirectory.repository.AdministratorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorProfileService {
    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    public AdministratorProfile getAdministratorProfile(Long userId) {
        return administratorProfileRepository.findById(userId).orElseThrow(() -> new RuntimeException("Administrator not found"));
    }

    public AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile) {
        return administratorProfileRepository.save(administratorProfile);
    }

    public void deleteAdministratorProfile(Long userId) {
        administratorProfileRepository.deleteById(userId);
    }
}

package com.nilotpal.collegedirectory.repository;

import com.nilotpal.collegedirectory.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {

    @Query("delete from FacultyProfile p where p.user.id = :userId")
    @Modifying
    @Transactional
    void delete(@Param("userId") String userId);
}

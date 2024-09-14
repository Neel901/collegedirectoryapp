package com.nilotpal.collegedirectory.repository;

import com.nilotpal.collegedirectory.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    @Query("SELECT p FROM StudentProfile p WHERE p.user.name = :username")
    List<StudentProfile> findByUsername(@Param("username") String username);

    @Query("SELECT p FROM StudentProfile p WHERE p.department.name = :deptName")
    List<StudentProfile> findByDepartmentName(@Param("deptName") String deptName);

    @Query("SELECT p FROM StudentProfile p WHERE p.year = :year")
    List<StudentProfile> findByYear(@Param("year") String year);

    @Query("delete from StudentProfile p where p.user.id = :userId")
    @Modifying
    @Transactional
    void delete(@Param("userId") String userId);
}
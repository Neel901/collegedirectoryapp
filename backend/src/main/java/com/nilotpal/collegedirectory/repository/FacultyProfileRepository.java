package com.nilotpal.collegedirectory.repository;

import com.nilotpal.collegedirectory.dto.StudentInfoDTO;
import com.nilotpal.collegedirectory.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {

    @Query("delete from FacultyProfile p where p.user.id = :userId")
    @Modifying
    @Transactional
    void delete(@Param("userId") String userId);

    @Query("select p from FacultyProfile p where p.user.id = :userId")
    FacultyProfile findByUserId(@Param("userId") String userId);

    @Query(value = """
            SELECT new com.nilotpal.collegedirectory.dto.StudentInfoDTO(u.name, u.email, u.phone) 
            FROM FacultyProfile f  
            JOIN StudentProfile s ON f.department.id = s.department.id  
            JOIN User u ON s.user.id = u.id  
            WHERE f.user.id = :facultyUserId
            """)
    List<StudentInfoDTO> findStudentsByFacultyDepartment(@Param("facultyUserId") String facultyUserId);
}

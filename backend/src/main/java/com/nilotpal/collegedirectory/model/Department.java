package com.nilotpal.collegedirectory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "department")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "department")
    private Set<StudentProfile> students;

    @OneToMany(mappedBy = "department")
    private Set<FacultyProfile> faculty;

    @OneToMany(mappedBy = "department")
    private Set<AdministratorProfile> administrators;

}
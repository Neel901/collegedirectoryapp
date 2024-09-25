package com.nilotpal.collegedirectory.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrator_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorProfile {
    @Id
    private Long userId;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

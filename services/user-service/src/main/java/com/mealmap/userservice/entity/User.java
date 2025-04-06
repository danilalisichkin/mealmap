package com.mealmap.userservice.entity;

import com.mealmap.userservice.entity.enums.UserRole;
import com.mealmap.userservice.entity.value.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_phone_number", columnList = "phone_number", unique = true),
        @Index(name = "idx_users_organization_id", columnList = "organization_id"),
        @Index(name = "idx_users_name", columnList = "first_name, last_name")})
public class User {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer organizationId;

    @Embedded
    private UserStatus status;

    @Column(nullable = false)
    private UserRole role;

    @Column(updatable = false)
    private LocalDate createdAt;
}

package com.mealmap.organizationservice.entity;

import com.mealmap.organizationservice.entity.enums.OrganizationType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizations", indexes = {
        @Index(name = "idx_organizations_upn", columnList = "upn", unique = true),
        @Index(name = "idx_organizations_name", columnList = "name"),
        @Index(name = "idx_organizations_type", columnList = "type")})
public class Organization {
    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer upn;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String legalAddress;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private OrganizationType type;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate createdAt;
}

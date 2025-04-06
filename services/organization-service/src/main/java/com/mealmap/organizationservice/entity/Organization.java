package com.mealmap.organizationservice.entity;

import com.mealmap.organizationservice.entity.enums.OrganizationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, updatable = false)
    private OrganizationType type;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;
}

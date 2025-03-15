package com.mealmap.healthservice.entity;

import com.mealmap.healthservice.entity.enums.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_physic_health", indexes = {
        @Index(name = "idx_user_physic_health_user_id", columnList = "user_id", unique = true)})
public class UserPhysicHealth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID userId;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false, updatable = false)
    private LocalDate birthDate;

    @Column(nullable = false, updatable = false)
    private Gender gender;

    @OneToOne(mappedBy = "physicHealth", cascade = CascadeType.ALL)
    private UserDiet diet;

    @OneToMany(mappedBy = "physicHealth", cascade = CascadeType.ALL)
    private List<UserPhysicHealthHistory> history;
}

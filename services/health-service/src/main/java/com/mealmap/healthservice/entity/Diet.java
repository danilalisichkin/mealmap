package com.mealmap.healthservice.entity;

import com.mealmap.healthservice.entity.enums.DietType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "diets", indexes = {
        @Index(name = "idx_diets_physic_health_id", columnList = "physic_health_id")})
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "physic_health_id")
    private PhysicHealth physicHealth;

    @Column(nullable = false)
    private DietType type;

    @Column(nullable = false)
    private Integer goalWeight;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate startDate;
}

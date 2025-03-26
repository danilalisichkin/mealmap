package com.mealmap.preferenceservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_preferences", indexes = {
        @Index(name = "idx_user_preferences_user_id", columnList = "user_id", unique = true)})
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID userId;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "userPreferences", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPreference> productPreferences;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "userPreferences", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryPreference> categoryPreferences;
}

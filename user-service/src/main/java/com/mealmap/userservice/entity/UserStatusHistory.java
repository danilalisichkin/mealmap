package com.mealmap.userservice.entity;

import com.mealmap.userservice.entity.enums.StatusEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Immutable
@Table(name = "user_status_history", indexes = {
        @Index(name = "idx_user_status_history_user_id", columnList = "user_id"),
        @Index(name = "idx_user_status_history_changed_by", columnList = "changed_by")})
public class UserStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private StatusEvent newStatus;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy;

    @CreationTimestamp
    private LocalDateTime eventAt;
}

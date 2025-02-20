package com.mealmap.userservice.entity.specification;

import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.UserStatusHistory;
import com.mealmap.userservice.entity.enums.StatusEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserStatusHistorySpecification {

    public static Specification<UserStatusHistory> withUser(User user) {
        return (root, query, criteriaBuilder) -> user != null
                ? criteriaBuilder.equal(root.get("user"), user)
                : criteriaBuilder.conjunction();
    }

    public static Specification<UserStatusHistory> withStatusEvent(StatusEvent statusEvent) {
        return (root, query, criteriaBuilder) -> statusEvent != null
                ? criteriaBuilder.equal(root.get("newStatus"), statusEvent)
                : criteriaBuilder.conjunction();
    }

    public static Specification<UserStatusHistory> createdAtAfter(LocalDateTime startTime) {
        return (root, query, criteriaBuilder) -> startTime != null
                ? criteriaBuilder.greaterThanOrEqualTo(root.get("eventAt"), startTime)
                : criteriaBuilder.conjunction();
    }

    public static Specification<UserStatusHistory> createdAtBefore(LocalDateTime endTime) {
        return (root, query, criteriaBuilder) -> endTime != null
                ? criteriaBuilder.lessThanOrEqualTo(root.get("eventAt"), endTime)
                : criteriaBuilder.conjunction();
    }
}

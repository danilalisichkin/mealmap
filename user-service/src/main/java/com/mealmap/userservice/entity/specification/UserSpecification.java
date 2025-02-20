package com.mealmap.userservice.entity.specification;

import com.mealmap.userservice.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserSpecification {

    public static Specification<User> hasFirstNameLike(String firstName) {
        return (root, query, criteriaBuilder) -> !firstName.isEmpty()
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(
                                root.get("firstName")), "%" + firstName.toLowerCase() + "%")
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> hasLastNameLike(String lastName) {
        return (root, query, criteriaBuilder) -> !lastName.isEmpty()
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(
                                root.get("lastName")), "%" + lastName.toLowerCase() + "%")
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> isActive != null
                ? criteriaBuilder.equal(root.get("isActive"), isActive)
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> isTemporaryBlocked(Boolean isTemporaryBlocked) {
        return (root, query, criteriaBuilder) -> isTemporaryBlocked != null
                ? criteriaBuilder.equal(root.get("isTemporaryBlocked"), isTemporaryBlocked)
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> isBlocked(Boolean isBlocked) {
        return (root, query, criteriaBuilder) -> isBlocked != null
                ? criteriaBuilder.equal(root.get("isBlocked"), isBlocked)
                : criteriaBuilder.conjunction();
    }
}

package com.mealmap.userservice.entity.specification;

import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.User_;
import com.mealmap.userservice.entity.value.UserStatus_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserSpecification {

    public static Specification<User> hasFirstNameLike(String firstName) {
        return (root, query, criteriaBuilder) -> !StringUtils.isBlank(firstName)
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(
                                root.get(User_.FIRST_NAME)), "%" + firstName.toLowerCase() + "%")
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> hasLastNameLike(String lastName) {
        return (root, query, criteriaBuilder) -> !StringUtils.isBlank(lastName)
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(
                                root.get(User_.LAST_NAME)), "%" + lastName.toLowerCase() + "%")
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> isActive != null
                ? criteriaBuilder.equal(root.get(User_.STATUS).get(UserStatus_.IS_ACTIVE), isActive)
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> isTemporaryBlocked(Boolean isTemporaryBlocked) {
        return (root, query, criteriaBuilder) -> isTemporaryBlocked != null
                ? criteriaBuilder.equal(root.get(User_.STATUS).get(UserStatus_.IS_TEMPORARY_BLOCKED), isTemporaryBlocked)
                : criteriaBuilder.conjunction();
    }

    public static Specification<User> isBlocked(Boolean isBlocked) {
        return (root, query, criteriaBuilder) -> isBlocked != null
                ? criteriaBuilder.equal(root.get(User_.STATUS).get(UserStatus_.IS_BLOCKED), isBlocked)
                : criteriaBuilder.conjunction();
    }
}

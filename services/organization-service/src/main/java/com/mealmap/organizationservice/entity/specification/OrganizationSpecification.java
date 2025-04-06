package com.mealmap.organizationservice.entity.specification;

import com.mealmap.organizationservice.entity.Organization;
import com.mealmap.organizationservice.entity.Organization_;
import com.mealmap.organizationservice.entity.enums.OrganizationType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrganizationSpecification {

    public static Specification<Organization> hasUpn(Integer upn) {
        return (root, query, criteriaBuilder) -> upn != null
                ? criteriaBuilder.equal(
                        root.get(Organization_.UPN), upn)
                : criteriaBuilder.conjunction();
    }

    public static Specification<Organization> hasNameLike(String name) {
        return (root, query, criteriaBuilder) -> !StringUtils.isBlank(name)
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(
                                root.get(Organization_.NAME)), "%" + name.toLowerCase() + "%")
                : criteriaBuilder.conjunction();
    }

    public static Specification<Organization> hasLegalAddressLike(String legalAddress) {
        return (root, query, criteriaBuilder) -> !StringUtils.isBlank(legalAddress)
                ? criteriaBuilder.like(
                        criteriaBuilder.lower(
                                root.get(Organization_.LEGAL_ADDRESS)), "%" + legalAddress.toLowerCase() + "%")
                : criteriaBuilder.conjunction();
    }

    public static Specification<Organization> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> !StringUtils.isBlank(phoneNumber)
                ? criteriaBuilder.equal(
                        root.get(Organization_.PHONE_NUMBER), phoneNumber)
                : criteriaBuilder.conjunction();
    }

    public static Specification<Organization> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> !StringUtils.isBlank(email)
                ? criteriaBuilder.equal(
                        root.get(Organization_.EMAIL), email)
                : criteriaBuilder.conjunction();
    }

    public static Specification<Organization> hasType(OrganizationType type) {
        return (root, query, criteriaBuilder) -> type != null
                ? criteriaBuilder.equal(
                        root.get(Organization_.TYPE), type)
                : criteriaBuilder.conjunction();
    }
}

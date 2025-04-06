package com.mealmap.authservice.validator;

import com.mealmap.authservice.client.OrganizationFeignClient;
import com.mealmap.authservice.client.dto.organization.OrganizationType;
import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.authservice.core.message.ApplicationMessages.USER_WITH_ROLE_CANT_BE_REGISTERED_IN_ORGANIZATION_WITH_TYPE;

@Component
@RequiredArgsConstructor
public class UserOrganizationValidator {
    private final OrganizationFeignClient organizationFeignClient;

    public void validateUserRelationToOrganization(Integer organizationId, UserRole userRole) {
        OrganizationType organizationType = organizationFeignClient.getOrganization(organizationId).getType();

        boolean isUserSupplierWorker = userRole.equals(UserRole.SUPPLIER_ADMIN)
                || userRole.equals(UserRole.SUPPLIER_EMPLOYEE);

        if (organizationType.equals(OrganizationType.SUPPLIER) && !isUserSupplierWorker) {
            throw new BadRequestException(
                    USER_WITH_ROLE_CANT_BE_REGISTERED_IN_ORGANIZATION_WITH_TYPE.formatted(
                            userRole.name(), organizationType.name()));
        }

        boolean isUserClientWorker = userRole.equals(UserRole.CLIENT_HEAD)
                || userRole.equals(UserRole.CLIENT_EMPLOYEE);

        if (organizationType.equals(OrganizationType.COMPANY) && !isUserClientWorker) {
            throw new BadRequestException(
                    USER_WITH_ROLE_CANT_BE_REGISTERED_IN_ORGANIZATION_WITH_TYPE.formatted(
                            userRole.name(), organizationType.name()));
        }
    }
}

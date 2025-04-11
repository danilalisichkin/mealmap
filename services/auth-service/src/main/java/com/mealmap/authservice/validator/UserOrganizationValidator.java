package com.mealmap.authservice.validator;

import com.mealmap.authservice.client.OrganizationFeignClient;
import com.mealmap.authservice.client.dto.organization.OrganizationType;
import com.mealmap.authservice.core.enums.Role;
import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.authservice.core.message.ApplicationMessages.USER_WITH_ROLE_CANT_BE_REGISTERED_IN_ORGANIZATION_WITH_TYPE;

@Component
@RequiredArgsConstructor
public class UserOrganizationValidator {
    private final OrganizationFeignClient organizationFeignClient;

    public void validateUserRelationToOrganization(Integer organizationId, Role role) {
        OrganizationType organizationType = organizationFeignClient.getOrganization(organizationId).getType();

        boolean isUserSupplierWorker = role.equals(Role.SUPPLIER);

        if (organizationType.equals(OrganizationType.SUPPLIER) && !isUserSupplierWorker) {
            throw new BadRequestException(
                    USER_WITH_ROLE_CANT_BE_REGISTERED_IN_ORGANIZATION_WITH_TYPE.formatted(
                            role.name(), organizationType.name()));
        }

        boolean isUserCustomerWorker = role.equals(Role.CUSTOMER);

        if (organizationType.equals(OrganizationType.CUSTOMER) && !isUserCustomerWorker) {
            throw new BadRequestException(
                    USER_WITH_ROLE_CANT_BE_REGISTERED_IN_ORGANIZATION_WITH_TYPE.formatted(
                            role.name(), organizationType.name()));
        }
    }
}

package com.mealmap.organizationservice.validator;

import com.mealmap.organizationservice.exception.ConflictException;
import com.mealmap.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.organizationservice.core.message.ApplicationMessages.ORGANIZATION_WITH_UPN_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class OrganizationValidator {
    private final OrganizationRepository organizationRepository;

    public void validateUpnUniqueness(Integer upn) {
        if (organizationRepository.existsByUpn(upn)) {
            throw new ConflictException(ORGANIZATION_WITH_UPN_ALREADY_EXISTS.formatted(upn.toString()));
        }
    }
}

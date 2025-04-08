package com.mealmap.organizationservice.service;

import com.mealmap.organizationservice.core.dto.filter.OrganizationFilter;
import com.mealmap.organizationservice.core.dto.organization.OrganizationCreationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationUpdatingDto;
import com.mealmap.organizationservice.core.enums.sort.OrganizationSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.springframework.data.domain.Sort;

public interface OrganizationService {
    PageDto<OrganizationDto> getPageOfOrganizations(
            Integer offset, Integer limit, OrganizationSortField sortBy, Sort.Direction sortOrder,
            OrganizationFilter filter);

    OrganizationDto getOrganization(Integer id);

    OrganizationDto createOrganization(OrganizationCreationDto organizationDto);

    OrganizationDto updateOrganization(Integer id, OrganizationUpdatingDto organizationDto);
}

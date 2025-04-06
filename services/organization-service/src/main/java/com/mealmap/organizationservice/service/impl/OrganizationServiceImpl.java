package com.mealmap.organizationservice.service.impl;

import com.mealmap.organizationservice.core.dto.filter.OrganizationFilter;
import com.mealmap.organizationservice.core.dto.organization.OrganizationCreationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationUpdatingDto;
import com.mealmap.organizationservice.core.dto.page.PageDto;
import com.mealmap.organizationservice.core.enums.sort.OrganizationSortField;
import com.mealmap.organizationservice.core.mapper.OrganizationMapper;
import com.mealmap.organizationservice.core.mapper.PageMapper;
import com.mealmap.organizationservice.entity.Organization;
import com.mealmap.organizationservice.repository.OrganizationRepository;
import com.mealmap.organizationservice.service.OrganizationService;
import com.mealmap.organizationservice.util.PageBuilder;
import com.mealmap.organizationservice.validator.OrganizationValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.organizationservice.core.message.ApplicationMessages.ORGANIZATION_NOT_FOUND;
import static com.mealmap.organizationservice.entity.specification.OrganizationSpecification.hasEmail;
import static com.mealmap.organizationservice.entity.specification.OrganizationSpecification.hasLegalAddressLike;
import static com.mealmap.organizationservice.entity.specification.OrganizationSpecification.hasNameLike;
import static com.mealmap.organizationservice.entity.specification.OrganizationSpecification.hasPhoneNumber;
import static com.mealmap.organizationservice.entity.specification.OrganizationSpecification.hasType;
import static com.mealmap.organizationservice.entity.specification.OrganizationSpecification.hasUpn;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "organizationCacheResolver")
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationValidator organizationValidator;

    private final OrganizationMapper organizationMapper;

    private final PageMapper pageMapper;

    private final OrganizationRepository organizationRepository;

    @Override
    @Cacheable
    public PageDto<OrganizationDto> getPageOfOrganizations(
            Integer offset, Integer limit, OrganizationSortField sortBy, Sort.Direction sortOrder,
            OrganizationFilter filter) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        Specification<Organization> spec = Specification
                .where(hasUpn(filter.getUpn()))
                .and(hasNameLike(filter.getName()))
                .and(hasLegalAddressLike(filter.getLegalAddress()))
                .and(hasPhoneNumber(filter.getPhoneNumber()))
                .and(hasEmail(filter.getEmail()))
                .and(hasType(filter.getType()));

        return pageMapper.pageToPageDto(
                organizationMapper.entityPageToDtoPage(
                        organizationRepository.findAll(spec, request)));
    }

    @Override
    @Cacheable(key = "#id")
    public OrganizationDto getOrganization(Integer id) {
        Organization organization = getOrganizationEntity(id);

        return organizationMapper.entityToDto(organization);
    }

    @Override
    @Transactional
    @CachePut(key = "#result.id")
    public OrganizationDto createOrganization(OrganizationCreationDto organizationDto) {
        organizationValidator.validateUpnUniqueness(organizationDto.getUpn());

        Organization organizationToCreate = organizationMapper.dtoToEntity(organizationDto);

        return organizationMapper.entityToDto(
                organizationRepository.save(organizationToCreate));
    }

    @Override
    @Transactional
    @CachePut(key = "#id")
    public OrganizationDto updateOrganization(Integer id, OrganizationUpdatingDto organizationDto) {
        Organization organizationToUpdate = getOrganizationEntity(id);

        if (!organizationDto.getUpn().equals(organizationToUpdate.getUpn())) {
            organizationValidator.validateUpnUniqueness(organizationDto.getUpn());
        }

        organizationMapper.updateEntityFromDto(organizationToUpdate, organizationDto);

        return organizationMapper.entityToDto(
                organizationRepository.save(organizationToUpdate));
    }

    private Organization getOrganizationEntity(Integer id) {
        return organizationRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ORGANIZATION_NOT_FOUND.formatted(id)));
    }
}

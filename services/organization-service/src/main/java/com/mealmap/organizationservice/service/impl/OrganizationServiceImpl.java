package com.mealmap.organizationservice.service.impl;

import com.mealmap.organizationservice.core.dto.filter.OrganizationFilter;
import com.mealmap.organizationservice.core.dto.organization.OrganizationCreationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationUpdatingDto;
import com.mealmap.organizationservice.core.enums.sort.OrganizationSortField;
import com.mealmap.organizationservice.core.mapper.OrganizationMapper;
import com.mealmap.organizationservice.entity.Organization;
import com.mealmap.organizationservice.entity.enums.OrganizationType;
import com.mealmap.organizationservice.repository.OrganizationRepository;
import com.mealmap.organizationservice.service.OrganizationService;
import com.mealmap.organizationservice.validator.OrganizationValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.starters.paginationstarter.mapper.PageMapper;
import com.mealmap.starters.paginationstarter.util.PageBuilder;
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
import static com.mealmap.organizationservice.core.message.ApplicationMessages.SUPPLIER_NOT_FOUND;
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
    @Cacheable
    public PageDto<OrganizationDto> getPageOfSuppliers(
            Integer offset, Integer limit, OrganizationSortField sortBy, Sort.Direction sortOrder) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        return pageMapper.pageToPageDto(
                organizationMapper.entityPageToDtoPage(
                        organizationRepository.findAllByType(OrganizationType.SUPPLIER, request)));
    }

    @Override
    @Cacheable(key = "#id")
    public OrganizationDto getOrganization(Integer id) {
        Organization organization = getOrganizationEntity(id);

        return organizationMapper.entityToDto(organization);
    }

    @Override
    @Cacheable(key = "'supplier_' + #id")
    public OrganizationDto getSupplier(Integer id) {
        Organization supplier = getSupplierEntity(id);

        return organizationMapper.entityToDto(supplier);
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

    private Organization getSupplierEntity(Integer id) {
        return organizationRepository
                .findByIdAndType(id, OrganizationType.SUPPLIER)
                .orElseThrow(() -> new ResourceNotFoundException(SUPPLIER_NOT_FOUND.formatted(id)));
    }
}

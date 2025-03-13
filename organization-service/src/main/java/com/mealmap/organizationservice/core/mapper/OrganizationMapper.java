package com.mealmap.organizationservice.core.mapper;

import com.mealmap.organizationservice.core.dto.organization.OrganizationCreationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationUpdatingDto;
import com.mealmap.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Organization dtoToEntity(OrganizationCreationDto dto);

    OrganizationDto entityToDto(Organization entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(@MappingTarget Organization entity, OrganizationUpdatingDto dto);

    List<OrganizationDto> entityListToDtoList(List<Organization> entityList);

    default Page<OrganizationDto> entityPageToDtoPage(Page<Organization> entityPage) {
        return new PageImpl<>(
                entityListToDtoList(entityPage.getContent()),
                entityPage.getPageable(),
                entityPage.getTotalElements());
    }
}

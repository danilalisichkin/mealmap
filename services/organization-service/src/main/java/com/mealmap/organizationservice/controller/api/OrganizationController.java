package com.mealmap.organizationservice.controller.api;

import com.mealmap.organizationservice.controller.doc.OrganizationControllerDoc;
import com.mealmap.organizationservice.core.dto.filter.OrganizationFilter;
import com.mealmap.organizationservice.core.dto.organization.OrganizationCreationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationUpdatingDto;
import com.mealmap.organizationservice.core.enums.sort.OrganizationSortField;
import com.mealmap.organizationservice.service.OrganizationService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizations")
public class OrganizationController implements OrganizationControllerDoc {
    private final OrganizationService organizationService;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<PageDto<OrganizationDto>> getPageOfOrganizations(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrganizationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid OrganizationFilter filter) {

        PageDto<OrganizationDto> page =
                organizationService.getPageOfOrganizations(offset, limit, sortBy, sortOrder, filter);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Override
    @GetMapping("/suppliers")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PageDto<OrganizationDto>> getPageOfSuppliers(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrganizationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        PageDto<OrganizationDto> page =
                organizationService.getPageOfSuppliers(offset, limit, sortBy, sortOrder);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("(hasRole('OPERATOR') and hasRole('ADMIN')) " +
            "or (isApplicationService() and hasRole('AUTH_SERVICE'))")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable Integer id) {
        OrganizationDto organization = organizationService.getOrganization(id);

        return ResponseEntity.status(HttpStatus.OK).body(organization);
    }

    @Override
    @GetMapping("/suppliers/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrganizationDto> getSupplier(@PathVariable Integer id) {

        OrganizationDto supplier = organizationService.getSupplier(id);

        return ResponseEntity.status(HttpStatus.OK).body(supplier);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<OrganizationDto> createOrganization(
            @RequestBody @Valid OrganizationCreationDto organizationDto) {

        OrganizationDto organization = organizationService.createOrganization(organizationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(organization);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and (isOrganizationMember(#id) or hasRole('OPERATOR'))")
    public ResponseEntity<OrganizationDto> updateOrganization(
            @PathVariable Integer id, @RequestBody @Valid OrganizationUpdatingDto organizationDto) {

        OrganizationDto organization = organizationService.updateOrganization(id, organizationDto);

        return ResponseEntity.status(HttpStatus.OK).body(organization);
    }
}

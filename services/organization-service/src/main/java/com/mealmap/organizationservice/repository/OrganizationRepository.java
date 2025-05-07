package com.mealmap.organizationservice.repository;

import com.mealmap.organizationservice.entity.Organization;
import com.mealmap.organizationservice.entity.enums.OrganizationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository
        extends JpaRepository<Organization, Integer>, JpaSpecificationExecutor<Organization> {

    boolean existsByUpn(Integer upn);

    Page<Organization> findAllByType(OrganizationType type, Pageable pageable);

    Optional<Organization> findByIdAndType(Integer id, OrganizationType type);
}

package com.mealmap.organizationservice.repository;

import com.mealmap.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository
        extends JpaRepository<Organization, Integer>, JpaSpecificationExecutor<Organization> {

    boolean existsByUpn(Integer upn);
}

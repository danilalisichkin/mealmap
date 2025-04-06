package com.mealmap.authservice.client;

import com.mealmap.authservice.client.config.OrganizationApiClientConfig;
import com.mealmap.authservice.client.dto.organization.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "organization-api",
        url = "${business.services.organization.url}",
        configuration = OrganizationApiClientConfig.class)
public interface OrganizationFeignClient {

    @GetMapping(value = "/{id}")
    OrganizationDto getOrganization(@PathVariable Integer id);
}

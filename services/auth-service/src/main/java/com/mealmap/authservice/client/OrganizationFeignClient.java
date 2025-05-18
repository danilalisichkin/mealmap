package com.mealmap.authservice.client;

import com.mealmap.authservice.client.config.FeignOAuth2Config;
import com.mealmap.authservice.client.config.OrganizationApiClientConfig;
import com.mealmap.authservice.client.dto.organization.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${business.services.organization.name}",
        path = "${business.services.organization.path}",
        configuration = {OrganizationApiClientConfig.class, FeignOAuth2Config.class})
public interface OrganizationFeignClient {

    @GetMapping(value = "/{id}")
    OrganizationDto getOrganization(@PathVariable Integer id);
}

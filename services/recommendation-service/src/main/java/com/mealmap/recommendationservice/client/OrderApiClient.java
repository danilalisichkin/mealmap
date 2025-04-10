package com.mealmap.recommendationservice.client;

import com.mealmap.recommendationservice.client.config.FeignOAuth2Config;
import com.mealmap.recommendationservice.client.config.OrderApiClientConfig;
import com.mealmap.recommendationservice.client.dto.order.OrderSortField;
import com.mealmap.recommendationservice.core.model.order.Order;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "order-api",
        url = "${business.services.order.url}",
        contextId = "orderClient",
        configuration = {OrderApiClientConfig.class, FeignOAuth2Config.class})
public interface OrderApiClient {
    @GetMapping("/{userId}/orders")
    PageDto<Order> getPageOfUserOrders(
            @PathVariable String userId,
            @RequestParam Integer offset,
            @RequestParam Integer limit,
            @RequestParam OrderSortField sortBy,
            @RequestParam Sort.Direction sortOrder);
}

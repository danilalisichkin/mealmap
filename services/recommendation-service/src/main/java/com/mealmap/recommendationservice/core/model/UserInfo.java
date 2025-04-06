package com.mealmap.recommendationservice.core.model;

import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import com.mealmap.recommendationservice.core.model.order.Order;
import com.mealmap.recommendationservice.core.model.preference.Preferences;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String userId;

    private PhysicHealth physicHealth;

    private Diet diet;

    private Preferences preferences;

    private List<Order> lastOrders;

    private LocalDateTime createdAt;
}

package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import com.mealmap.starters.notificationstarter.client.NotificationClient;
import com.mealmap.starters.notificationstarter.dto.Notification;
import com.mealmap.starters.notificationstarter.enums.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.mealmap.orderservice.notification.NotificationTemplates.ORDER_IS_IN_DELIVERY_MESSAGE;
import static com.mealmap.orderservice.notification.NotificationTemplates.ORDER_IS_IN_DELIVERY_SUBJECT;

@Component
@RequiredArgsConstructor
public class DeliveryOrderHandler implements OrderLifecycleHandler {
    private final NotificationClient notificationClient;

    @Override
    public void handle(Order order) {
        order.setStatus(OrderStatus.ON_THE_WAY);

        // TODO: send notification via AOP / Events
//        notificationClient.sendNotification(new Notification(
//                UUID.fromString(order.getUserId()),
//                Channel.TELEGRAM,
//                ORDER_IS_IN_DELIVERY_SUBJECT.formatted(order.getId().toHexString()),
//                ORDER_IS_IN_DELIVERY_MESSAGE
//        ));
    }

    @Override
    public OrderLifecyclePhase getSupportedPhase() {
        return OrderLifecyclePhase.DELIVERY;
    }
}

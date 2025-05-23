package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.PaymentStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import com.mealmap.starters.notificationstarter.client.NotificationClient;
import com.mealmap.starters.notificationstarter.dto.Notification;
import com.mealmap.starters.notificationstarter.enums.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.mealmap.orderservice.notification.NotificationTemplates.ORDER_IS_CANCELLED_MESSAGE;
import static com.mealmap.orderservice.notification.NotificationTemplates.ORDER_IS_CANCELLED_SUBJECT;

@Component
@RequiredArgsConstructor
public class CancellingOrderHandler implements OrderLifecycleHandler {
    private final NotificationClient notificationClient;

    @Override
    public void handle(Order order) {
//        if (order.getPaymentInfo().getPaymentStatus().equals(PaymentStatus.PAID)) {
//            // TODO: refund money to account via payment-service
//        }

        order.setStatus(OrderStatus.CANCELED);

        // TODO: send notification via AOP / Events
        notificationClient.sendNotification(new Notification(
                UUID.fromString(order.getUserId()),
                Channel.EMAIL,
                ORDER_IS_CANCELLED_SUBJECT.formatted(order.getId().toHexString()),
                ORDER_IS_CANCELLED_MESSAGE
        ));
    }

    @Override
    public OrderLifecyclePhase getSupportedPhase() {
        return OrderLifecyclePhase.CANCELLING;
    }
}

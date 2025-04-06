package com.mealmap.orderservice.notification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationTemplates {
    public static final String ORDER_IS_CANCELLED_SUBJECT =
            "Заказ №%s отменен";
    public static final String ORDER_IS_CANCELLED_MESSAGE =
            """
               Ваш заказ был отменен.
               
               Если вы считаете, что это ошибка, обратитесь в службу поддержки.
               
               С уважением, команда MealMap.
            """;

    public static final String ORDER_IS_IN_DELIVERY_SUBJECT =
            "Заказ №%s в пути";
    public static final String ORDER_IS_IN_DELIVERY_MESSAGE =
            """
               Ваш заказ уже в пути.
               
               Курьер свяжется с Вами, когда будет на месте.
               
               С уважением, команда MealMap.
            """;
}

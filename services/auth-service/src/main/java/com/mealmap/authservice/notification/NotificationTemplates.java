package com.mealmap.authservice.notification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationTemplates {
    public static final String USER_ACTIVATION_IS_PENDING_SUBJECT =
            "Регистрация пользователя";
    public static final String USER_ACTIVATION_IS_PENDING_MESSAGE =
            """
               Дорогой пользователь, Ваша регистрация прошла успешно.
               
               Ваша учетная запись в данный момент времени деактивирована.
               Для активации учетной записи свяжитесь с администратором Вашей компании.
               
               С уважением, команда MealMap.
            """;
}

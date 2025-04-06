package com.mealmap.userservice.notification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationTemplates {
    public static final String YOU_HAVE_BEEN_BLOCKED_SUBJECT =
            "Ваш аккаунт заблокирован!";
    public static final String YOU_HAVE_BEEN_BLOCKED_MESSAGE =
            """
               %s, Вы были заблокированы!
               
               Причина блокировки: %s. Если вы считаете, что это ошибка, обратитесь в службу поддержки.
               
               С уважением, команда MealMap.
            """;

    public static final String YOU_HAVE_BEEN_TEMPORARY_BLOCKED_SUBJECT =
            "Ваш аккаунт временно заблокирован!";
    public static final String YOU_HAVE_BEEN_TEMPORARY_BLOCKED_MESSAGE =
            """
               %s, Вы были временно заблокированы!
               
               Причина блокировки: %s. Если вы считаете, что это ошибка, обратитесь в службу поддержки.
               
               С уважением, команда MealMap.
            """;

    public static final String YOU_HAVE_BEEN_UNBLOCKED_SUBJECT =
            "Ваш аккаунт разблокирован!";
    public static final String YOU_HAVE_BEEN_UNBLOCKED_MESSAGE =
            """
               %s, Вы были разблокированы.
               
               Вы снова можете пользоваться услугами нашего сервиса.
               
               С уважением, команда MealMap.
            """;

    public static final String YOU_HAVE_BEEN_DEACTIVATED_SUBJECT =
            "Ваша учетная запись деактивирован!";
    public static final String YOU_HAVE_BEEN_DEACTIVATED_MESSAGE =
            """
               %s, Ваша учетная запись была успешно деактивирована.
               
               Если мы Вам понадобимся, Вы можете восстановить свою учетную запись в любой момент.
               
               Надеемся, что Вы к нам вернетесь! С уважением, команда MealMap.
            """;

    public static final String YOU_HAVE_BEEN_ACTIVATED_SUBJECT =
            "Ваша учетная запись деактивирован!";
    public static final String YOU_HAVE_BEEN_ACTIVATED_MESSAGE =
            """
               %s, Ваша учетная запись была успешно активирована.
               
               Спасибо, что вернулись! Мы очень скучали.
               
               С уважением, команда MealMap.
            """;
}

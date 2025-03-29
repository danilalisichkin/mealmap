package com.mealmap.notificationservice.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.notifications")
public class NotificationsConfig {
    private final EmailProperties email;
    private final TelegramProperties telegram;
    private final SmsProperties sms;

    @Getter
    @AllArgsConstructor
    public static class EmailProperties {
        private final boolean enabled;
        private final Sender sender;

        @Getter
        @AllArgsConstructor
        public static class Sender {
            private final String email;
            private final String name;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class TelegramProperties {
        private final boolean enabled;
    }

    @Getter
    @AllArgsConstructor
    public static class SmsProperties {
        private final boolean enabled;
    }
}

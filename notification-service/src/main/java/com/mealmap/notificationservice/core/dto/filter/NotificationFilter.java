package com.mealmap.notificationservice.core.dto.filter;

import com.mealmap.notificationservice.document.enums.Channel;
import com.mealmap.notificationservice.document.enums.NotificationStatus;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.mealmap.notificationservice.validator.RangeValidator.isValidRange;

@Data
@Builder
@AllArgsConstructor
public class NotificationFilter {
    private Channel channel;

    private NotificationStatus status;

    @PastOrPresent
    private LocalDateTime sentAtStart;

    @PastOrPresent
    private LocalDateTime sentAtEnd;

    @AssertTrue(message = "sentAtStart must be before sentAtEnd")
    private boolean isValidOrderedAtRange() {
        return isValidRange(sentAtStart, sentAtEnd);
    }
}

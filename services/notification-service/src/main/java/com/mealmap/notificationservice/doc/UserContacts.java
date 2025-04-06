package com.mealmap.notificationservice.doc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("user_contacts")
public class UserContacts {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String userId;

    private String email;

    private String phoneNumber;

    private Long tgChatId;
}

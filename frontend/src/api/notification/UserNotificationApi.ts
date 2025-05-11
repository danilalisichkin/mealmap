import ApiClient from "./client/ApiClient";
import { NotificationDto } from "./dto/NotificationDto";
import { UserContactsDto } from "./dto/UserContactsDto";
import { NotificationCreationDto } from "./dto/NotificationCreationDto";
import { NotificationSortField } from "./enums/NotificationSortField";
import { PageDto } from "../common/dto/PageDto";

export const UserNotificationApi = {
  getUserNotifications: async (
    userId: string,
    offset: number = 0,
    limit: number = 10,
    sortBy: NotificationSortField = NotificationSortField.ID,
    sortOrder: "ASC" | "DESC" = "ASC"
  ): Promise<PageDto<NotificationDto>> => {
    const response = await ApiClient.get<PageDto<NotificationDto>>(
      `/users/${userId}/notifications`,
      {
        params: {
          offset,
          limit,
          sortBy,
          sortOrder,
        },
      }
    );
    return response.data;
  },

  getUserContacts: async (userId: string): Promise<UserContactsDto> => {
    const response = await ApiClient.get<UserContactsDto>(
      `/users/${userId}/contacts`
    );
    return response.data;
  },

  createUserNotification: async (
    userId: string,
    notificationDto: NotificationCreationDto
  ): Promise<NotificationDto> => {
    const response = await ApiClient.post<NotificationDto>(
      `/users/${userId}/notifications`,
      notificationDto
    );
    return response.data;
  },
};

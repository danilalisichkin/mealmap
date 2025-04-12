import ApiClient from "./client/ApiClient";
import { NotificationDto } from "./dto/NotificationDto";
import { NotificationCreationDto } from "./dto/NotificationCreationDto";
import { NotificationFilter } from "./dto/NotificationFilter";
import { NotificationSortField } from "./enums/NotificationSortField";

export const NotificationApi = {
    getNotifications: async (
        offset: number = 0,
        limit: number = 10,
        sortBy: NotificationSortField = NotificationSortField.ID,
        sortOrder: "ASC" | "DESC" = "ASC",
        filter?: NotificationFilter,
        search?: string
    ): Promise<NotificationDto[]> => {
        const response = await ApiClient.get<NotificationDto[]>("/notifications", {
            params: {
                offset,
                limit,
                sortBy,
                sortOrder,
                ...filter,
                search,
            },
        });
        return response.data;
    },

    createNotification: async (userId: string, notificationDto: NotificationCreationDto): Promise<NotificationDto> => {
        const response = await ApiClient.post<NotificationDto>(`/users/${userId}/notifications`, notificationDto);
        return response.data;
    },
};
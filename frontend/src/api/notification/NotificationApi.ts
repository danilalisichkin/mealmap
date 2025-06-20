import ApiClient from "./client/ApiClient";
import { NotificationDto } from "./dto/NotificationDto";
import { NotificationFilter } from "./dto/NotificationFilter";
import { NotificationSortField } from "./enums/NotificationSortField";
import { PageDto } from "../common/dto/PageDto";

export const NotificationApi = {
  getNotifications: async (
    offset: number = 0,
    limit: number = 10,
    sortBy: NotificationSortField = NotificationSortField.ID,
    sortOrder: "ASC" | "DESC" = "ASC",
    filter?: NotificationFilter,
    search?: string
  ): Promise<PageDto<NotificationDto>> => {
    const response = await ApiClient.get<PageDto<NotificationDto>>(
      "/notifications",
      {
        params: {
          offset,
          limit,
          sortBy,
          sortOrder,
          ...filter,
          search,
        },
      }
    );
    return response.data;
  },
};

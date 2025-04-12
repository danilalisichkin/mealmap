import ApiClient from "./client/ApiClient";
import { UserDto } from "./dto/UserDto";
import { UserUpdatingDto } from "./dto/UserUpdatingDto";
import { StatusHistoryDto } from "./dto/StatusHistoryDto";
import { StatusHistoryCreationDto } from "./dto/StatusHistoryCreationDto";
import { UserFilter } from "./dto/UserFilter";
import { UserStatusHistoryFilter } from "./dto/UserStatusHistoryFilter";
import { Role } from "./enums/Role";
import { StatusHistorySortField } from "./enums/StatusHistorySortField";
import { UserSortField } from "./enums/UserSortField";

export const UserApi = {
    getUsers: async (
        offset: number = 0,
        limit: number = 10,
        sortBy: UserSortField = UserSortField.ID,
        sortOrder: "ASC" | "DESC" = "ASC",
        filter?: UserFilter,
        name?: string
    ): Promise<UserDto[]> => {
        const response = await ApiClient.get<UserDto[]>("/users", {
            params: { offset, limit, sortBy, sortOrder, ...filter, name },
        });
        return response.data;
    },

    getUserById: async (id: string): Promise<UserDto> => {
        const response = await ApiClient.get<UserDto>(`/users/${id}`);
        return response.data;
    },

    updateUser: async (id: string, userDto: UserUpdatingDto): Promise<UserDto> => {
        const response = await ApiClient.put<UserDto>(`/users/${id}`, userDto);
        return response.data;
    },

    assignRole: async (id: string, role: Role): Promise<UserDto> => {
        const response = await ApiClient.post<UserDto>(`/users/${id}/role`, { role });
        return response.data;
    },

    unassignRole: async (id: string, role: Role): Promise<UserDto> => {
        const response = await ApiClient.delete<UserDto>(`/users/${id}/role`, { data: { role } });
        return response.data;
    },

    getUserStatusHistory: async (
        id: string,
        offset: number = 0,
        limit: number = 10,
        sortBy: StatusHistorySortField = StatusHistorySortField.ID,
        sortOrder: "ASC" | "DESC" = "ASC",
        filter?: UserStatusHistoryFilter
    ): Promise<StatusHistoryDto[]> => {
        const response = await ApiClient.get<StatusHistoryDto[]>(`/users/${id}/status-history`, {
            params: { offset, limit, sortBy, sortOrder, ...filter },
        });
        return response.data;
    },

    activateUser: async (id: string, statusDto: StatusHistoryCreationDto): Promise<StatusHistoryDto> => {
        const response = await ApiClient.post<StatusHistoryDto>(`/users/${id}/activate`, statusDto);
        return response.data;
    },

    deactivateUser: async (id: string, statusDto: StatusHistoryCreationDto): Promise<StatusHistoryDto> => {
        const response = await ApiClient.post<StatusHistoryDto>(`/users/${id}/deactivate`, statusDto);
        return response.data;
    },

    blockUser: async (id: string, statusDto: StatusHistoryCreationDto): Promise<StatusHistoryDto> => {
        const response = await ApiClient.post<StatusHistoryDto>(`/users/${id}/block`, statusDto);
        return response.data;
    },

    temporaryBlockUser: async (id: string, statusDto: StatusHistoryCreationDto): Promise<StatusHistoryDto> => {
        const response = await ApiClient.post<StatusHistoryDto>(`/users/${id}/temporary-block`, statusDto);
        return response.data;
    },

    unblockUser: async (id: string, statusDto: StatusHistoryCreationDto): Promise<StatusHistoryDto> => {
        const response = await ApiClient.post<StatusHistoryDto>(`/users/${id}/unblock`, statusDto);
        return response.data;
    },
};
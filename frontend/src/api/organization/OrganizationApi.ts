import ApiClient from "./client/ApiClient";
import { OrganizationDto } from "./dto/OrganizationDto";
import { OrganizationCreationDto } from "./dto/OrganizationCreationDto";
import { OrganizationUpdatingDto } from "./dto/OrganizationUpdatingDto";
import { OrganizationFilter } from "./dto/OrganizationFilter";
import { OrganizationSortField } from "./enums/OrganizationSortField";

export const OrganizationApi = {
    getOrganizations: async (
        offset: number = 0,
        limit: number = 10,
        sortBy: OrganizationSortField = OrganizationSortField.ID,
        sortOrder: "ASC" | "DESC" = "ASC",
        filter?: OrganizationFilter
    ): Promise<OrganizationDto[]> => {
        const response = await ApiClient.get<OrganizationDto[]>("/organizations", {
            params: {
                offset,
                limit,
                sortBy,
                sortOrder,
                ...filter,
            },
        });
        return response.data;
    },

    getOrganizationById: async (id: number): Promise<OrganizationDto> => {
        const response = await ApiClient.get<OrganizationDto>(`/organizations/${id}`);
        return response.data;
    },

    createOrganization: async (organizationDto: OrganizationCreationDto): Promise<OrganizationDto> => {
        const response = await ApiClient.post<OrganizationDto>("/organizations", organizationDto);
        return response.data;
    },

    updateOrganization: async (id: number, organizationDto: OrganizationUpdatingDto): Promise<OrganizationDto> => {
        const response = await ApiClient.put<OrganizationDto>(`/organizations/${id}`, organizationDto);
        return response.data;
    },
};
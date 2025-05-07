import ApiClient from "./client/ApiClient";
import { OrganizationDto } from "./dto/OrganizationDto";
import { OrganizationCreationDto } from "./dto/OrganizationCreationDto";
import { OrganizationUpdatingDto } from "./dto/OrganizationUpdatingDto";
import { OrganizationFilter } from "./dto/OrganizationFilter";
import { OrganizationSortField } from "./enums/OrganizationSortField";
import { PageDto } from "../common/dto/PageDto";

export const OrganizationApi = {
  getOrganizations: async (
    offset: number = 0,
    limit: number = 10,
    sortBy: OrganizationSortField = OrganizationSortField.ID,
    sortOrder: "ASC" | "DESC" = "ASC",
    filter?: OrganizationFilter
  ): Promise<PageDto<OrganizationDto>> => {
    const response = await ApiClient.get<PageDto<OrganizationDto>>(
      "/organizations",
      {
        params: {
          offset,
          limit,
          sortBy,
          sortOrder,
          ...filter,
        },
      }
    );
    return response.data;
  },

  getSuppliers: async (
    offset: number = 0,
    limit: number = 20,
    sortBy: OrganizationSortField = OrganizationSortField.NAME,
    sortOrder: "ASC" | "DESC" = "ASC",
  ): Promise<PageDto<OrganizationDto>> => {
    const response = await ApiClient.get<PageDto<OrganizationDto>>(
      "/organizations/suppliers",
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

  getOrganizationById: async (id: number): Promise<OrganizationDto> => {
    const response = await ApiClient.get<OrganizationDto>(
      `/organizations/${id}`
    );
    return response.data;
  },

  getSupplierById: async (id: number): Promise<OrganizationDto> => {
    const response = await ApiClient.get<OrganizationDto>(
      `/organizations/suppliers/${id}`
    );
    return response.data;
  },

  createOrganization: async (
    organizationDto: OrganizationCreationDto
  ): Promise<OrganizationDto> => {
    const response = await ApiClient.post<OrganizationDto>(
      "/organizations",
      organizationDto
    );
    return response.data;
  },

  updateOrganization: async (
    id: number,
    organizationDto: OrganizationUpdatingDto
  ): Promise<OrganizationDto> => {
    const response = await ApiClient.put<OrganizationDto>(
      `/organizations/${id}`,
      organizationDto
    );
    return response.data;
  },
};

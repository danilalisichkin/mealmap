import ApiClient from "./client/ApiClient";
import { PhysicHealthDto } from "./dto/PhysicHealthDto";
import { PhysicHealthCreationDto } from "./dto/PhysicHealthCreationDto";
import { PhysicHealthUpdatingDto } from "./dto/PhysicHealthUpdatingDto";
import { PhysicHealthHistoryDto } from "./dto/PhysicHealthHistoryDto";
import { DietDto } from "./dto/DietDto";
import { DietCreationDto } from "./dto/DietCreationDto";
import { DietUpdatingDto } from "./dto/DietUpdatingDto";

export const HealthApi = {
  getUserPhysicHealth: async (userId: string): Promise<PhysicHealthDto> => {
    const response = await ApiClient.get<PhysicHealthDto>(
      `/users/${userId}/physic-health`
    );
    return response.data;
  },

  getUserPhysicHealthHistory: async (
    userId: string
  ): Promise<PhysicHealthHistoryDto[]> => {
    const response = await ApiClient.get<PhysicHealthHistoryDto[]>(
      `/users/${userId}/physic-health/history`
    );
    return response.data;
  },

  getUserDiet: async (userId: string): Promise<DietDto> => {
    const response = await ApiClient.get<DietDto>(`/users/${userId}/diet`);
    return response.data;
  },

  createUserPhysicHealth: async (
    userId: string,
    data: PhysicHealthCreationDto
  ): Promise<PhysicHealthDto> => {
    const response = await ApiClient.post<PhysicHealthDto>(
      `/users/${userId}/physic-health`,
      data
    );
    return response.data;
  },

  createUserDiet: async (
    userId: string,
    data: DietCreationDto
  ): Promise<DietDto> => {
    const response = await ApiClient.post<DietDto>(
      `/users/${userId}/diet`,
      data
    );
    return response.data;
  },

  updateUserPhysicHealth: async (
    userId: string,
    data: PhysicHealthUpdatingDto
  ): Promise<PhysicHealthDto> => {
    const response = await ApiClient.put<PhysicHealthDto>(
      `/users/${userId}/physic-health`,
      data
    );
    return response.data;
  },

  updateUserDiet: async (
    userId: string,
    data: DietUpdatingDto
  ): Promise<DietDto> => {
    const response = await ApiClient.put<DietDto>(
      `/users/${userId}/diet`,
      data
    );
    return response.data;
  },

  deleteUserDiet: async (userId: string): Promise<void> => {
    await ApiClient.delete(`/users/${userId}/diet`);
  },
};

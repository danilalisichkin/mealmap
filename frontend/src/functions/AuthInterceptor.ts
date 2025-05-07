import { AxiosInstance } from "axios";
import { AuthApi } from "../api/auth/AuthApi";

let accessToken: string | null = null;
let refreshToken: string | null = null;

export const setAccessToken = (token: string | null) => {
  accessToken = token;
};

export const updateRefreshToken = (token: string | null) => {
  refreshToken = token;
};

export const attachAuthInterceptor = (apiClient: AxiosInstance) => {
  apiClient.interceptors.request.use(
    async (config) => {
      if (accessToken) {
        config.headers["Authorization"] = `Bearer ${accessToken}`;
      }
      return config;
    },
    (error) => Promise.reject(error)
  );

  apiClient.interceptors.response.use(
    (response) => response,
    async (error) => {
      const originalRequest = error.config;

      if (
        error.response?.status === 401 &&
        !originalRequest._retry &&
        refreshToken
      ) {
        originalRequest._retry = true;
        try {
          const response = await AuthApi.refreshToken(refreshToken);
          setAccessToken(response.accessToken);
          updateRefreshToken(response.refreshToken);
          originalRequest.headers[
            "Authorization"
          ] = `Bearer ${response.accessToken}`;
          return apiClient(originalRequest);
        } catch (err) {
          console.error("Ошибка при обновлении токена:", err);
        }
      }

      return Promise.reject(error);
    }
  );
};

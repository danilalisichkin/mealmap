import ApiClient from "./client/ApiClient";
import { KeycloakAccessTokenDto } from "./dto/KeycloakAccessTokenDto";
import { UserDto } from "./dto/UserDto";
import { UserLoginDto } from "./dto/UserLoginDto";
import { UserRegisterDto } from "./dto/UserRegisterDto";

export const AuthApi = {
    signIn: async (loginDto: UserLoginDto): Promise<KeycloakAccessTokenDto> => {
        const response = await ApiClient.post<KeycloakAccessTokenDto>("/auth/sign-in", loginDto);
        return response.data;
    },

    signUp: async (registerDto: UserRegisterDto): Promise<UserDto> => {
        const response = await ApiClient.post<UserDto>("/auth/sign-up", registerDto);
        return response.data;
    },

    refreshToken: async (refreshToken: string): Promise<KeycloakAccessTokenDto> => {
        const response = await ApiClient.post<KeycloakAccessTokenDto>("/auth/refresh-token", { refreshToken });
        return response.data;
    },
};
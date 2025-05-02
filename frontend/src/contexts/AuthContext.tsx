import React, { createContext, useContext, useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";
import { setAccessToken, updateRefreshToken } from "../functions/AuthInterceptor";
import { AuthApi } from "../api/auth/AuthApi";
import { KeycloakAccessTokenDto } from "../api/auth/dto/KeycloakAccessTokenDto";

interface AuthContextProps {
  userId: string | null;
  setToken: (token: KeycloakAccessTokenDto) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

const AUTH_PROPERTIES = {
  REFRESH_INTERVAL: 4 * 60 * 1000,
}

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [userId, setUserId] = useState<string | null>(null);
  const [refreshToken, setRefreshToken] = useState<string | null>(null);

  const setToken = (token: KeycloakAccessTokenDto) => {
    try {
      const decodedToken = jwtDecode<{ sub: string }>(token.accessToken);
      setUserId(decodedToken.sub);
      setAccessToken(token.accessToken);
      setRefreshToken(token.refreshToken);
      updateRefreshToken(token.refreshToken);
    } catch (error) {
      console.error("Ошибка при декодировании токена:", error);
    }
  };

  const logout = () => {
    setUserId(null);
    setAccessToken(null);
    setRefreshToken(null);
  };

  const refreshTokens = async () => {
    if (!refreshToken) return;

    try {
      const response = await AuthApi.refreshToken(refreshToken);
      setToken(response);
    } catch (error) {
      console.error("Ошибка при обновлении токенов:", error);
      logout();
    }
  };

  useEffect(() => {
    const interval = setInterval(() => {
      refreshTokens();
    }, AUTH_PROPERTIES.REFRESH_INTERVAL);
    return () => clearInterval(interval);
  }, [refreshToken]);

  return (
    <AuthContext.Provider value={{ userId, setToken, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextProps => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth должен использоваться внутри AuthProvider");
  }
  return context;
};

import React, { createContext, useContext, useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";
import {
  setAccessToken,
  updateRefreshToken,
} from "../functions/AuthInterceptor";
import { AuthApi } from "../api/auth/AuthApi";
import { KeycloakAccessTokenDto } from "../api/auth/dto/KeycloakAccessTokenDto";

interface AuthContextProps {
  userId: string | null;
  setAuth: (tokens: KeycloakAccessTokenDto) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

const AUTH_PROPERTIES = {
  REFRESH_INTERVAL: 4 * 60 * 1000,
};

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [userId, setUserId] = useState<string | null>(null);
  const [tokens, setTokens] = useState<KeycloakAccessTokenDto | null>(null);

  useEffect(() => {
    const storedTokens = localStorage.getItem("tokens");

    if (storedTokens) {
      try {
        const parsedTokens: KeycloakAccessTokenDto = JSON.parse(storedTokens);
        setTokens(parsedTokens);

        const decodedAccessToken = jwtDecode<{ sub: string }>(
          parsedTokens.accessToken
        );
        setUserId(decodedAccessToken.sub);
        setAccessToken(parsedTokens.accessToken);
        updateRefreshToken(parsedTokens.refreshToken);
      } catch (error) {
        console.error("Ошибка при декодировании токена:", error);
        logout();
      }
    }
  }, []);

  const setAuth = (tokens: KeycloakAccessTokenDto) => {
    try {
      const decodedToken = jwtDecode<{ sub: string }>(tokens.accessToken);
      setUserId(decodedToken.sub);
      setTokens(tokens);
      setAccessToken(tokens.accessToken);
      updateRefreshToken(tokens.refreshToken);

      localStorage.setItem("tokens", JSON.stringify(tokens));
    } catch (error) {
      console.error("Ошибка при декодировании токена:", error);
    }
  };

  const logout = () => {
    setUserId(null);
    setAccessToken(null);

    localStorage.removeItem("tokens");
  };

  const refreshTokens = async () => {
    if (!tokens) return;

    try {
      const newTokens = await AuthApi.refreshToken(tokens.refreshToken);
      setTokens(newTokens);
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
  }, [tokens]);

  return (
    <AuthContext.Provider value={{ userId, setAuth, logout }}>
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

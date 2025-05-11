import ApiClient from "./client/ApiClient";

export const TelegramBotApi = {
  getBotWriteLink: async (): Promise<string> => {
    const response = await ApiClient.get<string>(`/telegram-bot/links/write`);
    return response.data;
  },

  getBotStartLink: async (userId: string): Promise<string> => {
    const response = await ApiClient.get<string>(`/telegram-bot/links/start`, {
      params: { userId },
    });
    return response.data;
  },
};

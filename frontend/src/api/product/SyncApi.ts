import ApiClient from "./client/ApiClient";

export const SyncApi = {
  syncProducts: async (): Promise<void> => {
    await ApiClient.post("/sync/products");
  },

  syncCategories: async (): Promise<void> => {
    await ApiClient.post("/sync/categories");
  },
};

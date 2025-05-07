import ApiClient from "./client/ApiClient";

export const FileApi = {
  uploadFile: async (filename: string, file: File): Promise<void> => {
    const formData = new FormData();
    formData.append("file", file);

    await ApiClient.post(`/files/${filename}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  },

  downloadFile: async (filename: string): Promise<Blob> => {
    const response = await ApiClient.get(`/files/${filename}`, {
      responseType: "blob",
    });
    return response.data;
  },

  deleteFile: async (filename: string): Promise<void> => {
    await ApiClient.delete(`/files/${filename}`);
  },
};

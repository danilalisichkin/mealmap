import axios from "axios";
import ApiConfig from "../../ApiConfig";

const ApiClient = axios.create({
  baseURL: ApiConfig.AUTH_API.BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export default ApiClient;

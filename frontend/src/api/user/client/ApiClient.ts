import axios from "axios";
import { attachAuthInterceptor } from "../../../functions/AuthInterceptor";
import ApiConfig from "../../ApiConfig";

const ApiClient = axios.create({
  baseURL: ApiConfig.USER_API.BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

attachAuthInterceptor(ApiClient);

export default ApiClient;

import axios from "axios";
import { attachAuthInterceptor } from "../../../functions/AuthInterceptor";

const ApiClient = axios.create({
  baseURL: "http://localhost:8081/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});

attachAuthInterceptor(ApiClient);

export default ApiClient;

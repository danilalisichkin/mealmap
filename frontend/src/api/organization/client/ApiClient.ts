import axios from "axios";

const ApiClient = axios.create({
    baseURL: "http://localhost:8087/api/v1",
    headers: {
        "Content-Type": "application/json",
    },
});

export default ApiClient;
const SERVER_HOST = "172.20.10.4";

const ApiConfig = {
  AUTH_API: {
    BASE_URL: `http://${SERVER_HOST}:8083/api/v1`,
  },
  CART_API: {
    BASE_URL: `http://${SERVER_HOST}:8085/api/v1`,
  },
  FILE_API: {
    BASE_URL: `http://${SERVER_HOST}:8093/api/v1`,
  },
  HEALTH_API: {
    BASE_URL: `http://${SERVER_HOST}:8089/api/v1`,
  },
  NOTIFICATION_API: {
    BASE_URL: `http://${SERVER_HOST}:8091/api/v1`,
  },
  ORDER_API: {
    BASE_URL: `http://${SERVER_HOST}:8084/api/v1`,
  },
  ORGANIZATION_API: {
    BASE_URL: `http://${SERVER_HOST}:8087/api/v1`,
  },
  PREFERENCE_API: {
    BASE_URL: `http://${SERVER_HOST}:8088/api/v1`,
  },
  PRODUCT_API: {
    BASE_URL: `http://${SERVER_HOST}:8081/api/v1`,
  },
  PROMO_API: {
    BASE_URL: `http://${SERVER_HOST}:8086/api/v1`,
  },
  RECOMMENDATION_API: {
    BASE_URL: `http://${SERVER_HOST}:8090/api/v1`,
  },
  TELEGRAM_BOT_API: {
    BASE_URL: `http://${SERVER_HOST}:8092/api/v1`,
  },
  USER_API: {
    BASE_URL: `http://${SERVER_HOST}:8082/api/v1`,
  },
};

export default ApiConfig;

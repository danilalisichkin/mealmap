FROM node:16 AS build
WORKDIR /app

ARG REACT_APP_SERVER_HOST
ENV REACT_APP_SERVER_HOST=$REACT_APP_SERVER_HOST

COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

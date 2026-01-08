FROM node:20-alpine AS build

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci
COPY . .
RUN npm run build -- --configuration production

FROM nginx:1.23.3

LABEL org.opencontainers.image.title="frontend"
LABEL org.opencontainers.image.authors="XOzymandias"
LABEL org.opencontainers.image.version="0.0.0-SNAPSHOT"

ARG VERSION="0.0.0-SNAPSHOT"
ENV VERSION=${VERSION}

EXPOSE 5000

COPY --from=build /app/dist/Frontend/browser/ /usr/share/nginx/html/
COPY docker/etc/nginx/templates/ /etc/nginx/templates/
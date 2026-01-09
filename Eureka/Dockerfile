FROM eclipse-temurin:21-jre

LABEL org.opencontainers.image.title="Eureka"
LABEL org.opencontainers.image.authors="XOzymandias"
LABEL org.opencontainers.image.version="0.0.0-SNAPSHOT"

ARG VERSION="0.0.0-SNAPSHOT"
ENV VERSION=${VERSION}

COPY target/eureka-${VERSION}.jar eureka.jar
ENTRYPOINT ["java","-jar","eureka.jar"]

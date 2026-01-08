FROM eclipse-temurin:21.0.8_9-jre

LABEL org.opencontainers.image.title="ApiGateway"
LABEL org.opencontainers.image.authors="XOzymandias"
LABEL org.opencontainers.image.version="0.0.0-SNAPSHOT"

ARG VERSION="0.0.0-SNAPSHOT"
ENV VERSION=${VERSION}

COPY target/apiGateway-${VERSION}.jar apiGateway.jar

CMD ["java", "-jar", "apiGateway.jar"]
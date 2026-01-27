FROM eclipse-temurin:21.0.8_9-jre

LABEL org.opencontainers.image.title="Exercise"
LABEL org.opencontainers.image.authors="XOzymandias"
LABEL org.opencontainers.image.version="0.0.0-SNAPSHOT"

ARG VERSION="0.0.0-SNAPSHOT"
ENV VERSION=${VERSION}

COPY target/exercise-${VERSION}.jar exercise.jar

CMD ["sh", "-c", "sleep 20 && exec java -jar exercise.jar"]

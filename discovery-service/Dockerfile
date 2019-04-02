FROM openjdk:8u201-jre-alpine
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar discovery-service.jar
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8761/actuator/health || exit 1
COPY build/libs/discovery-service.jar .

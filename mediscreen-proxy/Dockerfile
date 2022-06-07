ARG BUILD_HOME=/mediscreen-proxy

FROM gradle:7.2.0-jdk8 as build-image
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME
COPY --chown=gradle:gradle build.gradle $APP_HOME/
COPY --chown=gradle:gradle src $APP_HOME/src
RUN gradle --no-daemon bootJar

FROM openjdk:8-jdk-alpine
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
RUN apk --no-cache add curl
COPY --from=build-image $APP_HOME/build/libs/MediscreenProxy-*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
EXPOSE 8080
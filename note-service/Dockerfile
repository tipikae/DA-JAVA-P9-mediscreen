ARG BUILD_HOME=/note-service

FROM gradle:7.2.0-jdk11-alpine as build-image
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
COPY --from=build-image $APP_HOME/build/libs/NoteService-*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
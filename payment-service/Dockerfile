FROM gradle:8-jdk21-alpine AS build
WORKDIR /app

COPY build.gradle settings.gradle .
COPY gradlew .
COPY gradle/wrapper/gradle-wrapper.jar gradle/wrapper/
COPY gradle/wrapper/gradle-wrapper.properties gradle/wrapper/

RUN gradle dependencies

COPY src ./src

RUN gradle bootJar -x test

FROM amazoncorretto:21-alpine
VOLUME /tmp

COPY --from=build /app/build/libs/*.jar payment-service.jar
ENTRYPOINT [ "java", "-jar", "/payment-service.jar" ]

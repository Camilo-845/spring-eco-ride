#!/bin/sh

echo "Building all modules..."

echo "Building module: config-server (in background)"
(cd config-service && ./gradlew bootJar -x test) &

echo "Building module: api-gateway (in background)"
(cd api-gateway && ./gradlew bootJar -x test) &

echo "Building module: payment-service (in background)"
(cd payment-service && ./gradlew bootJar -x test) &

echo "Building module: passager-service (in background)"
(cd passager-service && ./mvnw clean package -DskipTests &

wait

echo "Build completed"

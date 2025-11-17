# ECO-RIDE LATAM - Microservicios

Plataforma de **carpooling corporativo**: conductores (empleados) publican rutas y asientos disponibles; pasajeros (empleados) reservan; el sistema cobra un monto simbólico por trayecto y coordina notificaciones. Solución **reactiva, segura y escalable**.

[problem requirements](./requirements.md)

## Stack Tecnológico

- **Spring Cloud**: Gateway, OpenFeign, Config, Eureka, Resilience4j, Sleuth/Micrometer.
- **Mensajería**: Kafka (o RabbitMQ).
- **Base de Datos**: PostgreSQL por servicio. Migraciones con Liquibase/Flyway.
- **Infraestructura Dev**: Docker Compose con Keycloak, Kafka, Postgres, Zipkin/Tempo, Prometheus, Grafana.
- **Documentación**: OpenAPI 3 (springdoc-openapi).
- **Pruebas**: JUnit5, Mockito, Testcontainers, Spring Cloud Contract.

## Instalación y Uso

1.  **Clonar Repositorio**:

    ```bash
    git clone https://github.com/camilo-845/spring-eco-ride.git
    ```

2.  **Levantar Microservicios y servicios de infraestructura**:
    ```bash
    docker-compose up -d --build
    ```

## Uso de instancias de desarrollo

Para hacer pruebas en local de forma más rapida que la construcción del docker-compose por defecto

1. Ejecutar build de todos los servicios

   ```bash
   ./devTools/build-all-modules.sh
   ```

2. Ejecutar docker-compose dedicado para dev
   ```bash
   docker compose -f ./docker-compose.dev.yml up
   ```

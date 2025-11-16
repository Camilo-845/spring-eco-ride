# ECO-RIDE LATAM - Microservicios

Plataforma de **carpooling corporativo**: conductores (empleados) publican rutas y asientos disponibles; pasajeros (empleados) reservan; el sistema cobra un monto simbólico por trayecto y coordina notificaciones. Solución **reactiva, segura y escalable**.

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

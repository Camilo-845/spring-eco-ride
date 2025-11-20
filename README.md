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

### Obtener credenciales (tokens de acceso) para pruebas en desarrollo

Para probar los endpoints protegidos en desarrollo se debe obtener las credenciales, para evitar el proceso que se recomientda para producción pero de una multitud de pasos que retrasan en desarrollo, se puede configurar keycloak para que nos permita obtener estas credenciales de una manera más sencilla

1. Ir a la consola de adminitrador de keycloak http://localhost:8090/admin/master/console/
2. En la barra lateral ir a `Manage realms`
3. Seleccionar relam `ecoride`
4. En la barra lateral ir a `Clients`
5. Seleccionar client `eco-gateway`
6. En la sección Capability config

- Activar Client authentication
- Activar Autorization
- Activar el flow Direct access grant

7. Ir a la pestaña Credentials
8. Copiar el `Client Secret`, que es el que vamos a usar para obtener las credenciales

La petición para obtener las creadenciales es:

```sh
curl -X POST 'http://localhost:8090/realms/ecoride/protocol/openid-connect/token' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data 'client_id=eco-gateway' \
  --data 'client_secret=[Client Secret]' \
  --data 'username=[Username]' \
  --data 'password=[Password]' \
  --data 'grant_type=password'
```

Teniendo un usuario previamente creado se pude remplazar los datos de la consulta y obtener los tokens

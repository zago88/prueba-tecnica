# Plan de Desarrollo del Proyecto Pagos Service API

## Paso 1: Planificación

- Análisis de requisitos basado en el ejercicio proporcionado.
- Definición de arquitectura de capas: Presentación, Negocio, Datos.
- Identificación de tecnologías: Java 17, Spring Boot 3.3.0, MongoDB 6.0, RabbitMQ 3.12, Docker.

## Paso 2: Preparación del Entorno

- Creación de contenedores Docker para MongoDB y RabbitMQ.
- Configuración de Dockerfile para el servicio Java.
- Configuración de `docker-compose.yml` para orquestar servicios.

## Paso 3: Desarrollo

- Implementación del modelo de datos (`Pago`).
- Implementación de DTOs (`CambioEstatusRequest`).
- Implementación de servicio de negocio (`PagoService`).
- Implementación de controladores REST (`PagoController`).
- Implementación de publicación de eventos a RabbitMQ (`PagoPublisher`).
- Implementación de dos listeners (`PagoEventListener` y `VerificacionEventListener`).

## Paso 4: Pruebas

- Creación de pruebas unitarias con JUnit 5 y Mockito.
- Corrección de pruebas hasta lograr ejecución exitosa.
- Validación de API mediante Postman.

## Paso 5: Documentación

- Generación de esquema JSON (`pago-schema.json`).
- Documentación de Exchanges/Queues/Mensajes (`queues-and-messages.md`).
- Creación de colección de Postman para pruebas.
- Integración de Swagger UI para documentación automática.
- Integración de Actuator para healthchecks.

## Paso 6: Entrega

- Separación de documentación en la carpeta `docs/`.
- Actualización del `README.md` principal con índice de navegación.


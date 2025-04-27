
### ⚙️ `docs/setup-dev.md` (instrucciones para entorno local)

# Setup de Entorno de Desarrollo

## Requisitos Previos

- Java 17
- Docker + Docker Compose
- Maven o Gradle
- Make (opcional)

## Pasos

1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/tu-repo.git
cd tu-repo
```
2. Construir la aplicación(JAR)

```bash
./mvnw clean package -DskipTests
```

3. Levantar servicios con Docker:

```bash
docker-compose up --build -d
```

Esto levantará:

    MongoDB en el puerto 27017

    RabbitMQ en el puerto 5672 (AMQP) y 15672 (web)

    Servicio de Pagos en el puerto 8080

    Acceso rápido:

    API Swagger: http://localhost:8080/swagger-ui.html

    RabbitMQ Management UI: http://localhost:15672 (guest/guest)

5. Verifica estado de los contenedores:

```bash
docker ps
```

6. Detener el entorno:

```bash
docker-compose down
```

Notas

    El perfil activo por defecto es dev.

    El contenedor de la aplicación depende de que mongo y rabbitmq estén disponibles.

    La base de datos usada es pagosdb en MongoDB.

    Variables de conexión a MongoDB y RabbitMQ están configuradas automáticamente en el docker-compose.yml.

    

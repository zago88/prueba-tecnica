# Usar una imagen oficial de Eclipse Temurin (Java 17)
FROM eclipse-temurin:17-jdk-jammy as builder

# Crear directorio de trabajo
WORKDIR /app

# Copiar solo el artefacto final al contenedor
COPY pagos-service/target/pagos-service-*.jar app.jar

# Imagen final minimalista
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copiar sólo el JAR desde la fase builder
COPY --from=builder /app/app.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]


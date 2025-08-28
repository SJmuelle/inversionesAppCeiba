# Etapa de build (usa Maven 3.9 + JDK 21)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos los POM primero para aprovechar caché de dependencias
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Ahora copiamos el código y compilamos
COPY src ./src
RUN mvn -q -e -DskipTests package

# Etapa de runtime (JDK 21 "slim")
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el JAR construido
COPY --from=build /app/target/*.jar app.jar

# Render asigna el puerto en la env var $PORT; Spring lo tomará por -Dserver.port
ENV JAVA_OPTS="-Xms256m -Xmx512m"
CMD ["sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -jar app.jar"]

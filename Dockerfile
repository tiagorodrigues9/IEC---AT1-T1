# Estágio 1: Build da aplicação
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o pom.xml e baixa as dependências (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests -B

# Estágio 2: Imagem final
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia o JAR do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]


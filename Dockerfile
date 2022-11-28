FROM eclipse-temurin:11-jdk-alpine
ARG JAR_FILE=cheku/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "-Dspring.datasource.url=jdbc:mysql://${APP_DB}:3306/demo", "/app.jar"]
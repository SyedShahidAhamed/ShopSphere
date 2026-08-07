FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/shopsphere.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
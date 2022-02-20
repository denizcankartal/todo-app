FROM openjdk:8-jre-alpine
ARG JAR_FILE_LOCAL_PATH=target/todo-1.0.jar
WORKDIR /app
COPY JAR_FILE_LOCAL_PATH /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
FROM openjdk:11
VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8888

ENTRYPOINT ["java","-jar","/app.jar"]

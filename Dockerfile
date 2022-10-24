FROM openjdk:11-jdk

ARG JAR_FILE=./build/libs/hello-spring-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} springblog.jar

ENTRYPOINT ["java","-jar", "/springblog.jar"]
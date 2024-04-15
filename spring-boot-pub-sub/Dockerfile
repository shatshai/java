# Use a base image with Java 17 and Maven installed
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the Maven project and build the application
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Dpackaging=war

# Create a lightweight image with the built WAR file
FROM tomcat:latest
WORKDIR /usr/local/tomcat/webapps
COPY --from=build /app/target/spring-boot-pub-sub-0.0.1-SNAPSHOT.war ./ROOT.war

# Specify the command to run Tomcat
CMD ["catalina.sh", "run"]
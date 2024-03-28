FROM openjdk:23-jdk-oracle

WORKDIR /app

COPY src src

RUN javac -d . src/hrapp/*.java

CMD ["java", "hrapp.HRApp"]
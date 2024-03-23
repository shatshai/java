FROM openjdk:23-jdk-oracle

WORKDIR /app

COPY src src

RUN javac -d . src/com/shs/helloworld/HelloWorld.java

CMD ["java", "com.shs.helloworld.HelloWorld"]
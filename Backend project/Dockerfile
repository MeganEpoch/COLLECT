FROM openjdk:8-jdk-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
EXPOSE 8081
ADD target/collect-0.0.1.jar collect-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/collect-0.0.1.jar"]
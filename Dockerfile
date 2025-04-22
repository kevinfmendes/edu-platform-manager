FROM quay.io/quarkus/quarkus-micro-image:2.0

COPY target/*-runner.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/app.jar"]
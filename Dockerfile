FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine
COPY "./target/banca-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]
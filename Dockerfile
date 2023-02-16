FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
EXPOSE 8080
COPY target/radio-history-0.0.1-SNAPSHOT.jar /opt/app/radio-history.jar
CMD ["java", "-jar", "/opt/app/radio-history.jar"]

FROM amazoncorretto:11

RUN mkdir /opt/app
COPY build/libs/tendo-survey-1.0-SNAPSHOT.jar /opt/app

EXPOSE 8080
EXPOSE 5432

CMD ["java", "-jar", "/opt/app/tendo-survey-1.0-SNAPSHOT.jar"]
FROM amazoncorretto:11

RUN mkdir /opt/app
COPY build/libs/tendo-survey-1.0-SNAPSHOT.jar /opt/app

EXPOSE 8080
EXPOSE 5432

ENV DB_HOSTNAME $DB_HOSTNAME
ENV DB_PASSWORD $DB_PASSWORD

CMD ["java", "-jar", "/opt/app/tendo-survey-1.0-SNAPSHOT.jar"]
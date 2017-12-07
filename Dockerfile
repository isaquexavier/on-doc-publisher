FROM java:8

COPY build/libs/on-doc-publisher-0.0.1-SNAPSHOT.jar /usr/src/
WORKDIR /usr/src/

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=UNKNOWN_ENV_NAME /usr/src/on-doc-publisher-0.0.1-SNAPSHOT.jar

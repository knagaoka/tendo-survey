# Getting Started

## Locally
### Build the jar

`./gradlew clean build bootJar --refresh-dependencies`

### Run the app

`java -DDB_HOSTNAME=localhost:5432 -jar build/libs/tendo-survey-1.0-SNAPSHOT.jar`

## Docker
### Build the image

`docker build -t tendo-surveys .`

### Run the container

`docker run -t -d -p 8080:8080 tendo-surveys`

### View the logs

`docker logs -f {containerid}`
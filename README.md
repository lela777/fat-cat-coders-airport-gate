## Airport gate scheduling application
### Java application with Spring framework and a Postgres database with docker set up

In order to run this application you'll have to install [docker](https://docs.docker.com/get-docker/) and [docker-compose](https://docs.docker.com/compose/install/)

Project structure:
```
.
├── backend
│   ├── Dockerfile
│   └── ...
├── db
│   └── password.txt
├── docker-compose.yaml
└── README.md

```

[_docker-compose.yaml_](docker-compose.yaml)
```
version: "3.7"
services:
  backend:
    build: backend
    ports:
    - 8080:8080
  db:
    image: postgres
    ...
```
The compose file defines an application with two services `backend` and `db`.
When deploying the application, docker-compose maps port 8080 of the backend service container to port 8080 of the host as specified in the file.
Make sure port 8080 on the host is not already being in use.

## Start application with docker-compose

First you have to run following command in order to build docker images

``` 
$ docker-compose build
```
After that you'll can start containers with command: 
```
$ docker-compose up -d
Creating network "spring-postgres_default" with the default driver
Building backend
Step 1/11 : FROM maven:3.5-jdk-9 AS build
3.5-jdk-9: Pulling from library/maven
...
Successfully tagged spring-postgres_backend:latest
WARNING: Image for service backend was built because it did not already exist. To rebuild this image you must use `docker-compose build` or `docker-compose up --build`.
Creating spring-postgres_backend_1 ... done
Creating spring-postgres_db_1      ... done
```

## Expected result

Listing containers must show two containers running and the port mapping as below:
```
$ docker ps
CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS                  NAMES
56236f640eaa        postgres                  "docker-entrypoint.s…"   29 seconds ago      Up 28 seconds       5432/tcp               spring-postgres_db_1
6e69472dc2c0        spring-postgres_backend   "java -Djava.securit…"   29 seconds ago      Up 28 seconds       0.0.0.0:8080->8080/tcp   spring-postgres_backend_1
```

After the application starts, navigate to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) in your web browser. 
You can find [Swagger UI](https://swagger.io/tools/swagger-ui/) built for REST controllers of this application

You can stop containers with: 
```
$ docker-compose down
Stopping spring-postgres_db_1      ... done
Stopping spring-postgres_backend_1 ... done
Removing spring-postgres_db_1      ... done
Removing spring-postgres_backend_1 ... done
Removing network spring-postgres_default
```

# quarkus-reactive-panache


This is a minimal CRUD example exposing a couple of endpoints over REST.

This example is using:
 - RESTEasy Reactive to expose the REST endpoints
 - Hibernate Reactive with Panache to perform the CRUD operations on the database
 - A PostgreSQL database
 - ArC, the CDI inspired dependency injection tool with zero overhead

## Requirements

To compile and run this demo you will need:

- JDK 11+
- GraalVM

In addition, you will need either a PostgreSQL database, or Docker to run one.

## Building the example

> ./mvnw package

## Running the example

#### Prepare a PostgreSQL instance

#### Run the example in dev mode

> ./mvnw quarkus:dev

#### Run the example in JVM mode
> ./mvnw package
>
> java -jar ./target/quarkus-app/quarkus-run.jar


#### Run the example as a native application

> ./mvnw package -Dnative
>
> ./target/quarkus-reactive-panache-1.0.0-SNAPSHOT-runner


#### Running the example in Kubernetes

This section provides extra information for running both the database and the demo on Kubernetes.
As well as running the DB on Kubernetes, a service needs to be exposed for the demo to connect to the DB.

Then, rebuild demo docker image with a system property that points to the DB. 

```bash
-Dquarkus.datasource.reactive.url=vertx-reactive:postgresql://<DB_SERVICE_NAME>/quarkus
```

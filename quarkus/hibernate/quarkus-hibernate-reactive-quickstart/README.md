# quarkus-hibernate-reactive-quickstart

This is a minimal CRUD example exposing a couple of endpoints over REST,
with a front-end based on Angular so you can play with it from your browser.

This is using:
 - RESTEasy Reactive to expose the REST endpoints
 - Hibernate Reactive to perform the CRUD operations on the database
 - A PostgreSQL database
 - ArC, the CDI inspired dependency injection tool with zero overhead

## Requirements

To compile and run this demo you will need:

- JDK 11+
- GraalVM

In addition, you will need either a PostgreSQL database, or Docker to run one.


### Building and running in dev mode

Launch the Maven build on the checked out sources of this demo:

> ./mvnw package
>
> ./mvnw quarkus:dev


### Running in JVM mode

> ./mvnw package
>
> java -jar ./target/quarkus-app/quarkus-run.jar

### Running in native mode

> ./mvnw package -Dnative
>
> ./target/quarkus-hibernate-reactive-quickstart-1.0.0-SNAPSHOT-runner

### See the app in your browser

<http://localhost:8080/index.html>

### Running the example in Kubernetes

This section provides extra information for running both the database and the demo on Kubernetes.
As well as running the DB on Kubernetes, a service needs to be exposed for the demo to connect to the DB.

Then, rebuild demo docker image with a system property that points to the DB. 

>
>-Dquarkus.datasource.reactive.url=jdbc:postgresql://<DB_SERVICE_NAME>/quarkus_test
>

### --------------------

#### Description: https://jreact.com/index.php/2024/01/10/quarkus-hibernate-reactive-quickstart/


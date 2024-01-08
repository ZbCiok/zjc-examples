# quarkus-hibernate-orm-quickstart

This is a minimal CRUD example exposing a couple of endpoints over REST.

This is using:
 - RESTEasy to expose the REST endpoints
 - Hibernate ORM to perform the CRUD operations on the database
 - A PostgreSQL database; see below to run one via Docker
 - ArC, the CDI inspired dependency injection tool with zero overhead
 - The high performance Agroal connection pool
 - Infinispan based caching
 - All safely coordinated by the Narayana Transaction Manager

### Requirements

To compile and run this app you will need:

- JDK 11+
- GraalVM

In addition, you will need either a PostgreSQL database, or Docker to run one.


### Building and running in dev mode

> ./mvnw package
> 
> ./mvnw quarkus:dev


### Running in JVM mode
> ./mvnw package
>
> java -jar target/quarkus-app/quarkus-run.jar


### Running in native mode
> ./mvnw package -Dnative
> 
> ./target/quarkus-hibernate-orm-quickstart-1.0.0-SNAPSHOT-runner

## See the app in your browser
<http://localhost:8080/index.html>

### ---------
#### Description: https://jreact.com/index.php/2024/01/08/quarkus-hibernate-orm-quickstart/




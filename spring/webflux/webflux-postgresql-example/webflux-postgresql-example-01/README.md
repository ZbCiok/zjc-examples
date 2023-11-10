# webflux-postgresql-example-01
### R2DBC
### WebFlux http://jreact.com/index.php/crud/webflux/
We will build a Spring WebFlux R2DBC example that makes CRUD Operations with PostgreSQL database – a Organization application in that:
- Each Organization has id, name, description, status.
- Organization APIs help to create, retrieve, update, delete Organizations.
- Organization APIs also support custom finder methods such as find by status or by title.
### Run
- mvn spring-boot:run
- http://localhost:8080/api/organizations

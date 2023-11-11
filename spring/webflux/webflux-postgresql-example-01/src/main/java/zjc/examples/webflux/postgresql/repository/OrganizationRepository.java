package zjc.examples.webflux.postgresql.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import zjc.examples.webflux.postgresql.model.Organization;

import reactor.core.publisher.Flux;

@Repository
public interface OrganizationRepository extends R2dbcRepository<Organization, Integer>{
  Flux<Organization> findByNameContaining(String name);
  
  Flux<Organization> findByStatus(boolean isStatus);
}

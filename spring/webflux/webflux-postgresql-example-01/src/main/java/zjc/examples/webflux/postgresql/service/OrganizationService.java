package zjc.examples.webflux.postgresql.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zjc.examples.webflux.postgresql.model.Organization;
import zjc.examples.webflux.postgresql.repository.OrganizationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrganizationService {

  @Autowired
  OrganizationRepository organizationRepository;

  public Flux<Organization> findAll() {
    return organizationRepository.findAll();
  }

  public Flux<Organization> findByTitleContaining(String title) {
    return organizationRepository.findByNameContaining(title);
  }

  public Mono<Organization> findById(int id) {
    return organizationRepository.findById(id);
  }

  public Mono<Organization> save(Organization organization) {
    return organizationRepository.save(organization);
  }

  public Mono<Organization> update(int id, Organization organization) {
    return organizationRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
        .flatMap(optionalOrganization -> {
          if (optionalOrganization.isPresent()) {
            organization.setId(id);
            return organizationRepository.save(organization);
          }

          return Mono.empty();
        });
  }

  public Mono<Void> deleteById(int id) {
    return organizationRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return organizationRepository.deleteAll();
  }

  public Flux<Organization> findByStatus(boolean isStatus) {
    return organizationRepository.findByStatus(isStatus);
  }
}

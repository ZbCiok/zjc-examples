package zjc.examples.webflux.postgresql.controller;

import zjc.examples.webflux.postgresql.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import zjc.examples.webflux.postgresql.model.Organization;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OrganizationController {
  @Autowired
  OrganizationService organizationService;
  
  @GetMapping("/organizations")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Organization> getAllOrganizations(@RequestParam(required = false) String title) {
    if (title == null)
      return organizationService.findAll();
    else
      return organizationService.findByTitleContaining(title);
  }

  @GetMapping("/organizations/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Organization> getOrganizationById(@PathVariable("id") int id) {
    return organizationService.findById(id);
  }

  @PostMapping("/organizations")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Organization> createOrganization(@RequestBody Organization organization) {
    return organizationService.save(new Organization(organization.getName(), organization.getDescription(), false));
  }

  @PutMapping("/organizations/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Organization> updateOrganization(@PathVariable("id") int id, @RequestBody Organization organization) {
    return organizationService.update(id, organization);
  }

  @DeleteMapping("/organizations/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteOrganization(@PathVariable("id") int id) {
    return organizationService.deleteById(id);
  }

  @DeleteMapping("/organizations")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteAllOrganizations() {
    return organizationService.deleteAll();
  }

  @GetMapping("/organizations/status")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Organization> findByStatus() {
    return organizationService.findByStatus(true);
  }
}

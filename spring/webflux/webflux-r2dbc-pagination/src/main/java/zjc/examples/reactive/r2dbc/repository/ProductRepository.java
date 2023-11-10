package zjc.examples.reactive.r2dbc.repository;

import reactor.core.publisher.Mono;
import zjc.examples.reactive.r2dbc.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
//public interface ProductRepository extends ReactiveSortingRepository<Product, Integer> {
    Flux<Product> findAllBy(Pageable pageable);
}

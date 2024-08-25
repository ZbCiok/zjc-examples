package zjc.examples.spring.webflux.mongodb.service;

import zjc.examples.spring.webflux.mongodb.dto.ProductDto;
import zjc.examples.spring.webflux.mongodb.repository.ProductRepository;
import zjc.examples.spring.webflux.mongodb.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // Get all products
    public Flux<ProductDto> getProducts(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    // Get product using Id
    public Mono<ProductDto> getProduct(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    // Create Product
    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return  productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    // Update Product
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        return repository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    // Delete Product
    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}

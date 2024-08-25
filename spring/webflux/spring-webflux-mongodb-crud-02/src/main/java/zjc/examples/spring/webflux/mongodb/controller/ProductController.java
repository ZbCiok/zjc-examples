package zjc.examples.spring.webflux.mongodb.controller;

import zjc.examples.spring.webflux.mongodb.dto.ProductDto;
import zjc.examples.spring.webflux.mongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Controller class for managing Product-related HTTP requests
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // Get all products
    @GetMapping
    public Flux<ProductDto> getProducts(){
        return service.getProducts();
    }

    // GET product using Id
    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return service.getProduct(id);
    }

    // Create new Product
    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return service.saveProduct(productDtoMono);
    }

    // Update product using Id
    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id){
        return service.updateProduct(productDtoMono, id);
    }

    // Delete Product using Id
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }
}

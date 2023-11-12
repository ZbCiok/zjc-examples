package zjc.examples.reactive.r2dbc.controller;

import zjc.examples.reactive.r2dbc.entity.Product;
import zjc.examples.reactive.r2dbc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("getPage")
    public Mono<Page<Product>> getPage(@RequestParam("page") int page, @RequestParam("size") int size){
        return this.productService.getProducts(PageRequest.of(page, size));
    }

}

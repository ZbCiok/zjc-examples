package zjc.examples.spring.webflux.mongodb.utils;

import zjc.examples.spring.webflux.mongodb.dto.ProductDto;
import zjc.examples.spring.webflux.mongodb.entity.Product;
import org.springframework.beans.BeanUtils;

// Utility class for converting between Product and ProductDto objects
public class AppUtils {

    // Convert Product entity to ProductDto
    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    // Convert ProductDto to Product entity
    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}

package zjc.examples.spring.webflux.mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    // The ID of the product
    private String id;

    // The name of the product
    private String name;

    // The quantity of the product
    private int qty;

    // The price of the product
    private double price;
}

package zjc.example.quarkus.hibernate.reactive;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.name")
public class Product {

    @Id
    @SequenceGenerator(name = "productsSequence", sequenceName = "products_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "productsSequence")
    private Integer id;

    @Column(length = 40, unique = true)
    private String name;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" + id + "," + name + '}';
    }
}

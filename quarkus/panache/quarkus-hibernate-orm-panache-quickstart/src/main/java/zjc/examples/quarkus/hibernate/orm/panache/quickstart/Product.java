package zjc.examples.quarkus.hibernate.orm.panache.quickstart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue()
    Long id;

    @Column(length = 20)
    public String name;

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }
}
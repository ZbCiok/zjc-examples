package zjc.examples.hibernateReactive.data01;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "address", schema = "people")
public class Address {
    @Id
    Long id = null;

    @Column(name = "first_line")
    String firstLine;

    @Column(name = "second_line")
    String secondLine;

    String zip;
    String city;
    String state;
    String country;
}

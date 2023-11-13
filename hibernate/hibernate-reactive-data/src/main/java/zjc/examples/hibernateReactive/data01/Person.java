package zjc.examples.hibernateReactive.data01;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person", schema = "people")
public class Person {

    public Person() {}

    public Person(Long id, String firstName, String lastName, LocalDate birthdate, Set addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.addresses = addresses;
    }

    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private LocalDate birthdate;

    @ManyToMany
    @JoinTable(
            name = "person_address",
            schema = "people",
            joinColumns = {@JoinColumn(
                    name = "person_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "address_id"
            )}
    )
    @NotNull
    private Set<Address> addresses = new HashSet<Address>();



    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Set getAddresses() {
        return addresses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthdate, person.birthdate) && Objects.equals(addresses, person.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthdate, addresses);
    }
}

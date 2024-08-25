package zjc.examples.vertx.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Comment { 

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  @Column(nullable = true)
  private String content;

  @NotNull
  @JoinColumn(name = "product_id", nullable = false)
  @ManyToOne(cascade = CascadeType.MERGE)
  @JsonIgnore
  private Product product;


  public Comment() {
  }

  public Comment(String content) {
    this.content = content;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}

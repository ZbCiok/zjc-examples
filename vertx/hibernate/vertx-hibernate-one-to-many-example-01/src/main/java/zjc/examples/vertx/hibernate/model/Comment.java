package zjc.examples.vertx.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Comment { 

  @Id
  private Long id;

  @Column(nullable = true)
  private String content;

  @NotNull
  @ManyToOne(fetch = LAZY)
  private Product product;


  public Comment() {
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

package zjc.examples.webflux.postgresql.model;

import org.springframework.data.annotation.Id;

public class Organization {
  
  @Id
  private int id;

  private String name;

  private String description;

  private boolean status;

  public Organization() {

  }

  public Organization(String name, String description, boolean status) {
    this.name = name;
    this.description = description;
    this.status = status;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean isStatus) {
    this.status = isStatus;
  }

  @Override
  public String toString() {
    return "Organization [id=" + id + ", name=" + name + ", desc=" + description + ", status=" + status + "]";
  }

}

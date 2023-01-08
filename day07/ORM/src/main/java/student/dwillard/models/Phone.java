package student.dwillard.models;

import student.dwillard.annotations.OrmColumn;
import student.dwillard.annotations.OrmColumnId;
import student.dwillard.annotations.OrmEntity;

@OrmEntity(table = "phone")
public class Phone {
  @OrmColumnId
  private Long id;
  @OrmColumn(name = "number")
  private String number;
  @OrmColumn(name = "model")
  private String model;
  @OrmColumn(name = "price")
  private Integer price;
  @OrmColumn(name = "feature")
  private String mainFeature;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getMainFeature() {
    return mainFeature;
  }

  public void setMainFeature(String mainFeature) {
    this.mainFeature = mainFeature;
  }
}

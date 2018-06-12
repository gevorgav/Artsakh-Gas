package Core.Models;

public class Country {
  private Integer id;
  private String name;
  private String lat;
  private String lng;
  private String name_ENG;

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

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public String getName_ENG() {
    return name_ENG;
  }

  public void setName_ENG(String name_ENG) {
    this.name_ENG = name_ENG;
  }
}

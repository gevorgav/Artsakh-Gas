package Core.Models;

public class City {
  private Integer id;
  private String name;
  private Integer regionId;

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

  public Integer getRegionId() {
    return regionId;
  }

  public void setRegionId(Integer regionId) {
    this.regionId = regionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    City city = (City) o;

    if (id != null ? !id.equals(city.id) : city.id != null) return false;
    if (name != null ? !name.equals(city.name) : city.name != null) return false;
    return regionId != null ? regionId.equals(city.regionId) : city.regionId == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
    return result;
  }
}

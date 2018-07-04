package Models;

/**
 * Created by astghik.mamunc on 6/14/2018.
 */
public class Street {

    private Integer id;

    private String name;

    private Integer cityId;

    public Street() {
    }

    public Street(Integer id, String name, Integer cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Street street = (Street) o;

        if (id != null ? !id.equals(street.id) : street.id != null) return false;
        if (name != null ? !name.equals(street.name) : street.name != null) return false;
        return cityId != null ? cityId.equals(street.cityId) : street.cityId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Street street = new Street();
        street.setId(this.id);
        street.setName(this.name);
        street.setCityId(this.cityId);
        return street;
    }
}

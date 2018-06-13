package Models;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class GRP {

    private Integer id;

    private String name;

    private Integer cityId;

    public GRP() {
    }

    public GRP(Integer id, String name, Integer cityId) {
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

        GRP grp = (GRP) o;

        if (id != null ? !id.equals(grp.id) : grp.id != null) return false;
        if (name != null ? !name.equals(grp.name) : grp.name != null) return false;
        return cityId != null ? cityId.equals(grp.cityId) : grp.cityId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        return result;
    }
}

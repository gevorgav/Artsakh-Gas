package Models;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
public class Asht {

    private Integer id;

    private String name;

    private Integer regionId;

    public Asht() {
    }

    public Asht(Integer id, String name, Integer regionId) {
        this.id = id;
        this.name = name;
        this.regionId = regionId;
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

        Asht asht = (Asht) o;

        if (id != null ? !id.equals(asht.id) : asht.id != null) return false;
        if (name != null ? !name.equals(asht.name) : asht.name != null) return false;
        return regionId != null ? regionId.equals(asht.regionId) : asht.regionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Asht asht = new Asht();
        asht.setId(this.id);
        asht.setName(this.name);
        asht.setRegionId(this.regionId);
        return asht;
    }
}

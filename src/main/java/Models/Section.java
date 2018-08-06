package Models;

/**
 * Created by astghik.mamunc on 6/27/2018.
 */
public class Section {

    private Integer id;

    private String name;

    private Integer regionId;

    public Section() {
    }

    public Section(Integer id, String name, Integer regionId) {
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

        Section section = (Section) o;

        if (id != null ? !id.equals(section.id) : section.id != null) return false;
        if (name != null ? !name.equals(section.name) : section.name != null) return false;
        return regionId != null ? regionId.equals(section.regionId) : section.regionId == null;
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
        Section section = new Section();
        section.setId(this.id);
        section.setName(this.name);
        section.setRegionId(this.regionId);
        return section;
    }
}

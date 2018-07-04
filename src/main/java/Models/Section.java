package Models;

/**
 * Created by astghik.mamunc on 6/27/2018.
 */
public class Section {

    private Integer id;

    private String name;

    private Integer cityId;

    public Section() {
    }

    public Section(Integer id, String name, Integer cityId) {
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

        Section section = (Section) o;

        if (id != null ? !id.equals(section.id) : section.id != null) return false;
        if (name != null ? !name.equals(section.name) : section.name != null) return false;
        return cityId != null ? cityId.equals(section.cityId) : section.cityId == null;
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
        Section section = new Section();
        section.setId(this.id);
        section.setName(this.name);
        section.setCityId(this.cityId);
        return section;
    }
}

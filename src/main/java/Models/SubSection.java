package Models;

/**
 * Created by astghik.mamunc on 6/27/2018.
 */
public class SubSection {

    private Integer id;

    private String name;

    private Integer sectionId;

    public SubSection() {
    }

    public SubSection(Integer id, String name, Integer sectionId) {
        this.id = id;
        this.name = name;
        this.sectionId = sectionId;
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

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubSection that = (SubSection) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return sectionId != null ? sectionId.equals(that.sectionId) : that.sectionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SubSection subSection = new SubSection();
        subSection.setId(this.id);
        subSection.setName(this.name);
        subSection.setSectionId(this.sectionId);
        return subSection;
    }
}

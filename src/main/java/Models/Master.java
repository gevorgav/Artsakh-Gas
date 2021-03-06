package Models;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class Master {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer ashtId;

    private Integer sectionId;

    private Integer subSectionId;

    private Integer regionId;

    public Master() {
    }

    public Master(Integer id, String firstName, String lastName, Integer ashtId, Integer sectionId, Integer subSectionId, Integer regionId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ashtId = ashtId;
        this.sectionId = sectionId;
        this.subSectionId = subSectionId;
        this.regionId = regionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAshtId() {
        return ashtId;
    }

    public void setAshtId(Integer ashtId) {
        this.ashtId = ashtId;
    }

    public Integer getSubSectionId() {
        return subSectionId;
    }

    public void setSubSectionId(Integer subSectionId) {
        this.subSectionId = subSectionId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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

        Master master = (Master) o;

        if (id != null ? !id.equals(master.id) : master.id != null) return false;
        if (firstName != null ? !firstName.equals(master.firstName) : master.firstName != null) return false;
        if (lastName != null ? !lastName.equals(master.lastName) : master.lastName != null) return false;
        if (ashtId != null ? !ashtId.equals(master.ashtId) : master.ashtId != null) return false;
        if (sectionId != null ? !sectionId.equals(master.sectionId) : master.sectionId != null) return false;
        if (subSectionId != null ? !subSectionId.equals(master.subSectionId) : master.subSectionId != null)
            return false;
        return regionId != null ? regionId.equals(master.regionId) : master.regionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ashtId != null ? ashtId.hashCode() : 0);
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (subSectionId != null ? subSectionId.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Master master = new Master();
        master.setId(this.getId());
        master.setAshtId(this.ashtId);
        master.setLastName(this.lastName);
        master.setFirstName(this.firstName);
        master.setSubSectionId(this.subSectionId);
        master.setRegionId(this.regionId);
        master.setSectionId(this.sectionId);
        return master;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

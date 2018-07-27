package Models;

/**
 * Created by astghik.mamunc on 7/27/2018.
 */
public class Locksmith {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer ashtId;

    private Integer subSectionId;

    private Integer regionId;

    private Integer sectionId;

    public Locksmith() {
    }

    public Locksmith(Integer id, String firstName, String lastName, Integer ashtId, Integer subSectionId, Integer regionId, Integer sectionId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ashtId = ashtId;
        this.subSectionId = subSectionId;
        this.regionId = regionId;
        this.sectionId = sectionId;
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

        Locksmith locksmith = (Locksmith) o;

        if (id != null ? !id.equals(locksmith.id) : locksmith.id != null) return false;
        if (firstName != null ? !firstName.equals(locksmith.firstName) : locksmith.firstName != null) return false;
        if (lastName != null ? !lastName.equals(locksmith.lastName) : locksmith.lastName != null) return false;
        if (ashtId != null ? !ashtId.equals(locksmith.ashtId) : locksmith.ashtId != null) return false;
        if (subSectionId != null ? !subSectionId.equals(locksmith.subSectionId) : locksmith.subSectionId != null)
            return false;
        if (regionId != null ? !regionId.equals(locksmith.regionId) : locksmith.regionId != null) return false;
        return sectionId != null ? sectionId.equals(locksmith.sectionId) : locksmith.sectionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ashtId != null ? ashtId.hashCode() : 0);
        result = 31 * result + (subSectionId != null ? subSectionId.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Locksmith locksmith = new Locksmith();
        locksmith.setId(this.id);
        locksmith.setRegionId(this.regionId);
        locksmith.setSubSectionId(this.subSectionId);
        locksmith.setAshtId(this.ashtId);
        locksmith.setFirstName(this.firstName);
        locksmith.setLastName(this.lastName);
        locksmith.setSectionId(this.sectionId);
        return locksmith;
    }
}

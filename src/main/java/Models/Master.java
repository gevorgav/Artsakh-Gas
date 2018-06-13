package Models;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class Master {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer ashtId;

    public Master() {
    }

    public Master(Integer id, String firstName, String lastName, Integer ashtId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ashtId = ashtId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Master master = (Master) o;

        if (id != null ? !id.equals(master.id) : master.id != null) return false;
        if (firstName != null ? !firstName.equals(master.firstName) : master.firstName != null) return false;
        if (lastName != null ? !lastName.equals(master.lastName) : master.lastName != null) return false;
        return ashtId != null ? ashtId.equals(master.ashtId) : master.ashtId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ashtId != null ? ashtId.hashCode() : 0);
        return result;
    }
}

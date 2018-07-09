package Models;

/**
 * Created by astghik.mamunc on 6/14/2018.
 */
public class ViolationCode {

    private Integer id;

    private String name;

    private String description;

    public ViolationCode() {
    }

    public ViolationCode(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViolationCode that = (ViolationCode) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ViolationCode violationCode = new ViolationCode();
        violationCode.setId(this.id);
        violationCode.setName(this.name);
        violationCode.setDescription(this.description);
        return violationCode;
    }

    @Override
    public String toString() {
        return  name;
    }
}

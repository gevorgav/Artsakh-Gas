package Models;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
public class GRS {

    private Integer id;

    private String name;

    public GRS() {
    }

    public GRS(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GRS grs = (GRS) o;

        if (id != null ? !id.equals(grs.id) : grs.id != null) return false;
        return name != null ? name.equals(grs.name) : grs.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GRS grs = new GRS();
        grs.setId(this.id);
        grs.setName(this.name);
        return grs;
    }
}

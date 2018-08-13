package Core.Models;

/**
 * Created by ArtStyle on 25.02.2017.
 */
public class Month {
    public Month() {
    }

    public Month(Integer id, String name, Integer semiAnnualId) {
        this.id = id;
        this.name = name;
        this.semiAnnualId = semiAnnualId;
    }

    private Integer id;
    private String name;
    private Integer semiAnnualId;

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

    public Integer getSemiAnnualId() {
        return semiAnnualId;
    }

    public void setSemiAnnualId(Integer quarter) {
        this.semiAnnualId = quarter;
    }
}

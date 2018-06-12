package Core.Models;

/**
 * Created by ArtStyle on 25.02.2017.
 */
public class Month {
    public Month() {
    }

    public Month(Integer id, String name, Integer quarter) {
        this.id = id;
        this.name = name;
        this.quarter = quarter;
    }

    private Integer id;
    private String name;
    private Integer quarter;

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

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
}

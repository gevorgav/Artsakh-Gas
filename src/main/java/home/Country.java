package home;

/**
 * Created by gev on 15.03.2017.
 */
public class Country {
    private Integer id;
    private String name;
    private String name_ENG;
    private Integer count;

    public Country() {
    }

    public Country(Integer id, String name, String name_ENG, Integer count) {
        this.id = id;
        this.name = name;
        this.name_ENG = name_ENG;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_ENG() {
        return name_ENG;
    }

    public void setName_ENG(String name_ENG) {
        this.name_ENG = name_ENG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

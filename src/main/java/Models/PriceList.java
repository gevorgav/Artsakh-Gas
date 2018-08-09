package Models;

import java.util.Objects;

public class PriceList {

    private Integer id;

    private String name;

    private Double price;

    private String formula;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceList)) return false;
        PriceList priceList = (PriceList) o;
        return Objects.equals(getId(), priceList.getId()) &&
                Objects.equals(getName(), priceList.getName()) &&
                Objects.equals(getPrice(), priceList.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPrice());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PriceList priceList = new PriceList();
        priceList.setId(this.id);
        priceList.setName(this.name);
        priceList.setPrice(this.price);
        return priceList;
    }
}

package portfolio;

public class Portfoliocountry {
  public Portfoliocountry() {
  }

  public Portfoliocountry(Integer id, Integer portfolioid, Integer countryid, Integer count) {
    this.id = id;
    this.portfolioid = portfolioid;
    this.countryid = countryid;
    this.count = count;
  }

  private Integer id;
  private Integer portfolioid;
  private Integer countryid;
  private Integer count;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getPortfolioid() {
    return portfolioid;
  }

  public void setPortfolioid(Integer portfolioid) {
    this.portfolioid = portfolioid;
  }

  public Integer getCountryid() {
    return countryid;
  }

  public void setCountryid(Integer countryid) {
    this.countryid = countryid;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}

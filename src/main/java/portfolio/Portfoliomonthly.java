package portfolio;

import java.math.BigDecimal;

public class Portfoliomonthly {
  private Integer id;
  private Integer totaltouristcount;
  private Integer armtouristcount;
  private Integer othertouristcount;
  private BigDecimal finances;
  private Integer portfolioid;
  private Integer monthId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTotaltouristcount() {
    return totaltouristcount;
  }

  public void setTotaltouristcount(Integer totaltouristcount) {
    this.totaltouristcount = totaltouristcount;
  }

  public Integer getArmtouristcount() {
    return armtouristcount;
  }

  public void setArmtouristcount(Integer armtouristcount) {
    this.armtouristcount = armtouristcount;
  }

  public Integer getOthertouristcount() {
    return othertouristcount;
  }

  public void setOthertouristcount(Integer othertouristcount) {
    this.othertouristcount = othertouristcount;
  }

  public BigDecimal getFinances() {
    return finances;
  }

  public void setFinances(BigDecimal finances) {
    this.finances = finances;
  }

  public Integer getPortfolioid() {
    return portfolioid;
  }

  public void setPortfolioid(Integer portfolioid) {
    this.portfolioid = portfolioid;
  }

  public Integer getMonthId() {
    return monthId;
  }

  public void setMonthId(Integer monthId) {
    this.monthId = monthId;
  }
}

package portfolio;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Yearlyinforamtion {
  private Integer id;
  private BigDecimal gdp;
  private Integer overnightduration;
  private Integer ancaket1;
  private Integer ancaket2;
  private Integer ancaket3;
  private Integer amcaket4;
  private Integer ancaket5;
  private Integer yearId;
  private BigDecimal program1;
  private BigDecimal program2;
  private BigDecimal program3;
  private BigDecimal program4;
  private BigDecimal program5;
  private BigDecimal program6;
  private BigDecimal total;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getGdp() {
    return gdp;
  }

  public void setGdp(BigDecimal gdp) {
    this.gdp = gdp;
  }

  public Integer getOvernightduration() {
    return overnightduration;
  }

  public void setOvernightduration(Integer overnightduration) {
    this.overnightduration = overnightduration;
  }

  public Integer getAncaket1() {
    return ancaket1;
  }

  public void setAncaket1(Integer ancaket1) {
    this.ancaket1 = ancaket1;
  }

  public Integer getAncaket2() {
    return ancaket2;
  }

  public void setAncaket2(Integer ancaket2) {
    this.ancaket2 = ancaket2;
  }

  public Integer getAncaket3() {
    return ancaket3;
  }

  public void setAncaket3(Integer ancaket3) {
    this.ancaket3 = ancaket3;
  }

  public Integer getAmcaket4() {
    return amcaket4;
  }

  public void setAmcaket4(Integer amcaket4) {
    this.amcaket4 = amcaket4;
  }

  public Integer getAncaket5() {
    return ancaket5;
  }

  public void setAncaket5(Integer ancaket5) {
    this.ancaket5 = ancaket5;
  }

  public Integer getYearId() {
    return yearId;
  }

  public void setYearId(Integer yearId) {
    this.yearId = yearId;
  }

  public BigDecimal getProgram1() {
    return program1;
  }

  public void setProgram1(BigDecimal program1) {
    this.program1 = program1;
  }

  public BigDecimal getProgram2() {
    return program2;
  }

  public void setProgram2(BigDecimal program2) {
    this.program2 = program2;
  }

  public BigDecimal getProgram3() {
    return program3;
  }

  public void setProgram3(BigDecimal program3) {
    this.program3 = program3;
  }

  public BigDecimal getProgram4() {
    return program4;
  }

  public void setProgram4(BigDecimal program4) {
    this.program4 = program4;
  }

  public BigDecimal getProgram5() {
    return program5;
  }

  public void setProgram5(BigDecimal program5) {
    this.program5 = program5;
  }

  public BigDecimal getProgram6() {
    return program6;
  }

  public void setProgram6(BigDecimal program6) {
    this.program6 = program6;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public String getTotal() {
    NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
    DecimalFormat df = (DecimalFormat) nf;
    df.applyPattern("#,##0.00");
    return df.format(program1.add(program2).add(program3).add(program4).add(program5).add(program6));
  }

  public BigDecimal getTotalBigDecimal() {
    return program1.add(program2).add(program3).add(program4).add(program5).add(program6);
  }
}

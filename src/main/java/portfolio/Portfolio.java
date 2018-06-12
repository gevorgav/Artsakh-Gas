package portfolio;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Portfolio {
  private Integer id;
  private Integer quarter;
  private Integer totaltouristcount;
  private Integer armtouristcount;
  private Integer othertouristcount;
  private BigDecimal finances;
  private Integer ictouristcount;
  private Integer icmalecount;
  private Integer icfemalecount;
  private String icvisitdecription;
  private Integer socialpackagecount;
  private Integer transportid;
  private Integer column_12;
  private Integer istouroperator;
  private Integer age15;
  private Integer age30;
  private Integer age50;
  private Integer age51;
  private Integer year;
  private BigDecimal program1;
  private BigDecimal program2;
  private BigDecimal program3;
  private BigDecimal program4;
  private BigDecimal program5;
  private BigDecimal program6;
  private BigDecimal total;
  private BigDecimal financeArm;
  private BigDecimal financeForeign;
  private BigDecimal totalFinanceArm;
  private BigDecimal totalFinanceForeign;
  private Integer icStepanakert;
  private Integer icShushi;
  private Integer icTigranakert;
  private Integer icTsaxkashat;
  private BigDecimal providedProgram1;
  private BigDecimal providedProgram2;
  private BigDecimal providedProgram3;
  private BigDecimal providedProgram4;
  private BigDecimal providedProgram5;
  private BigDecimal providedProgram6;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuarter() {
    return quarter;
  }

  public void setQuarter(Integer quarter) {
    this.quarter = quarter;
  }

  public Integer getTotaltouristcount() {
    this.totaltouristcount = armtouristcount + othertouristcount;
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
    finances = totalFinanceArm.add(totalFinanceForeign);
    return finances;
  }

  public void setFinances(BigDecimal finances) {
    this.finances = finances;
  }

  public Integer getIctouristcount() {
    return ictouristcount;
  }

  public void setIctouristcount(Integer ictouristcount) {
    this.ictouristcount = ictouristcount;
  }

  public Integer getIcmalecount() {
    return icmalecount;
  }

  public void setIcmalecount(Integer icmalecount) {
    this.icmalecount = icmalecount;
  }

  public Integer getIcfemalecount() {
    return icfemalecount;
  }

  public void setIcfemalecount(Integer icfemalecount) {
    this.icfemalecount = icfemalecount;
  }

  public String getIcvisitdecription() {
    return icvisitdecription;
  }

  public void setIcvisitdecription(String icvisitdecription) {
    this.icvisitdecription = icvisitdecription;
  }

  public Integer getSocialpackagecount() {
    return socialpackagecount;
  }

  public void setSocialpackagecount(Integer socialpackagecount) {
    this.socialpackagecount = socialpackagecount;
  }

  public Integer getTransportid() {
    return transportid;
  }

  public void setTransportid(Integer transportid) {
    this.transportid = transportid;
  }

  public Integer getColumn_12() {
    return column_12;
  }

  public void setColumn_12(Integer column_12) {
    this.column_12 = column_12;
  }

  public Integer getIstouroperator() {
    return istouroperator;
  }

  public void setIstouroperator(Integer istouroperator) {
    this.istouroperator = istouroperator;
  }

  public Integer getAge15() {
    return age15;
  }

  public void setAge15(Integer age15) {
    this.age15 = age15;
  }

  public Integer getAge30() {
    return age30;
  }

  public void setAge30(Integer age30) {
    this.age30 = age30;
  }

  public Integer getAge50() {
    return age50;
  }

  public void setAge50(Integer age50) {
    this.age50 = age50;
  }

  public Integer getAge51() {
    return age51;
  }

  public void setAge51(Integer age51) {
    this.age51 = age51;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
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

  public BigDecimal getFinanceArm() {
    return financeArm;
  }

  public void setFinanceArm(BigDecimal financeArm) {
    this.financeArm = financeArm;
  }

  public BigDecimal getFinanceForeign() {
    return financeForeign;
  }

  public void setFinanceForeign(BigDecimal financeForeign) {
    this.financeForeign = financeForeign;
  }

  public BigDecimal getTotalFinanceArm() {
    totalFinanceArm = financeArm.multiply(new BigDecimal(armtouristcount));
    return totalFinanceArm;
  }

  public void setTotalFinanceArm(BigDecimal totalFinanceArm) {
    this.totalFinanceArm = totalFinanceArm;
  }

  public BigDecimal getTotalFinanceForeign() {
    totalFinanceForeign = financeForeign.multiply(new BigDecimal(othertouristcount));
    return totalFinanceForeign;
  }

  public void setTotalFinanceForeign(BigDecimal totalFinanceForeign) {
    this.totalFinanceForeign = totalFinanceForeign;
  }

  public Integer getIcStepanakert() {
    return icStepanakert;
  }

  public void setIcStepanakert(Integer icStepanakert) {
    this.icStepanakert = icStepanakert;
  }

  public Integer getIcShushi() {
    return icShushi;
  }

  public void setIcShushi(Integer icShushi) {
    this.icShushi = icShushi;
  }

  public Integer getIcTigranakert() {
    return icTigranakert;
  }

  public void setIcTigranakert(Integer icTigranakert) {
    this.icTigranakert = icTigranakert;
  }

  public Integer getIcTsaxkashat() {
    return icTsaxkashat;
  }

  public void setIcTsaxkashat(Integer icTsaxkashat) {
    this.icTsaxkashat = icTsaxkashat;
  }

  public BigDecimal getProvidedProgram1() {
    return providedProgram1;
  }

  public void setProvidedProgram1(BigDecimal providedProgram1) {
    this.providedProgram1 = providedProgram1;
  }

  public BigDecimal getProvidedProgram2() {
    return providedProgram2;
  }

  public void setProvidedProgram2(BigDecimal providedProgram2) {
    this.providedProgram2 = providedProgram2;
  }

  public BigDecimal getProvidedProgram3() {
    return providedProgram3;
  }

  public void setProvidedProgram3(BigDecimal providedProgram3) {
    this.providedProgram3 = providedProgram3;
  }

  public BigDecimal getProvidedProgram4() {
    return providedProgram4;
  }

  public void setProvidedProgram4(BigDecimal providedProgram4) {
    this.providedProgram4 = providedProgram4;
  }

  public BigDecimal getProvidedProgram5() {
    return providedProgram5;
  }

  public void setProvidedProgram5(BigDecimal providedProgram5) {
    this.providedProgram5 = providedProgram5;
  }

  public BigDecimal getProvidedProgram6() {
    return providedProgram6;
  }

  public void setProvidedProgram6(BigDecimal providedProgram6) {
    this.providedProgram6 = providedProgram6;
  }

  public String getTotal() {
    NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
    DecimalFormat df = (DecimalFormat) nf;
    df.applyPattern("#,##0.00");
    return df.format(program1.add(program2).add(program3).add(program4).add(program5).add(program6));
  }

  public String getProvidedTotal() {
    NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
    DecimalFormat df = (DecimalFormat) nf;
    df.applyPattern("#,##0.00");
    return df.format(providedProgram1.add(providedProgram2).add(providedProgram3).add(providedProgram4).add(providedProgram5).add(providedProgram6));
  }

  public String getTotalBalance() {
    NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
    DecimalFormat df = (DecimalFormat) nf;
    df.applyPattern("#,##0.00");
    return  df.format((providedProgram1.add(providedProgram2).add(providedProgram3).add(providedProgram4).add(providedProgram5).add(providedProgram6)).subtract(program1.add(program2).add(program3).add(program4).add(program5).add(program6)));
  }

  @Override
  public String toString() {
    return "Portfolio{" +
            "id=" + id +
            ", quarter=" + quarter +
            ", totaltouristcount=" + totaltouristcount +
            ", armtouristcount=" + armtouristcount +
            ", othertouristcount=" + othertouristcount +
            ", finances=" + finances +
            ", ictouristcount=" + ictouristcount +
            ", icmalecount=" + icmalecount +
            ", icfemalecount=" + icfemalecount +
            ", icvisitdecription='" + icvisitdecription + '\'' +
            ", socialpackagecount=" + socialpackagecount +
            ", transportid=" + transportid +
            ", column_12=" + column_12 +
            ", istouroperator=" + istouroperator +
            ", age15=" + age15 +
            ", age30=" + age30 +
            ", age50=" + age50 +
            ", age51=" + age51 +
            ", year=" + year +
            ", program1=" + program1 +
            ", program2=" + program2 +
            ", program3=" + program3 +
            ", program4=" + program4 +
            ", program5=" + program5 +
            ", program6=" + program6 +
            '}';
  }
}

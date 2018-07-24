package Models;

import java.util.Date;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class ClientHistory {

    private Integer id;

    private String clientId;

    private String violationActNumber;

    private Integer visitType;

    private String visitDescription;

    private Date updateDate;

    private Date previousVisitDate;

    private Date nextVisitDate;

    private String stampNumbers;

    private Integer go1;

    private Integer bacakaGo1;

    private Integer go2;

    private Integer bacakaGo2;

    private Integer go3;

    private Integer bacakaGo3;

    private Integer go4;

    private Integer bacakaGo4;

    private Integer go5;

    private Integer bacakaGo5;

    private Integer go6;

    private Integer bacakaGo6;

    private Integer jth;

    private Integer bacakaJth;

    private Integer jtt;

    private Integer bacakaJtt;

    private Integer ket;

    private Integer bacakaKet;

    private Integer jk;

    private Integer bacakaJk;

    private Integer jv;

    private Integer bacakaJv;

    private Integer jah;

    private Integer pakan;

    private String JTLog;

    private String risk;

    private Integer masterId;

    private boolean isPaid;

    private Integer regionId;

    private Integer semiAnnualId;

    public ClientHistory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getViolationActNumber() {
        return violationActNumber;
    }

    public void setViolationActNumber(String violationActNumber) {
        this.violationActNumber = violationActNumber;
    }

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }

    public String getVisitDescription() {
        return visitDescription;
    }

    public void setVisitDescription(String visitDescription) {
        this.visitDescription = visitDescription;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getPreviousVisitDate() {
        return previousVisitDate;
    }

    public void setPreviousVisitDate(Date previousVisitDate) {
        this.previousVisitDate = previousVisitDate;
    }

    public Date getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(Date nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public String getStampNumbers() {
        return stampNumbers;
    }

    public void setStampNumbers(String stampNumbers) {
        this.stampNumbers = stampNumbers;
    }

    public Integer getGo1() {
        return go1;
    }

    public void setGo1(Integer go1) {
        this.go1 = go1;
    }

    public Integer getBacakaGo1() {
        return bacakaGo1;
    }

    public void setBacakaGo1(Integer bacakaGo1) {
        this.bacakaGo1 = bacakaGo1;
    }

    public Integer getGo2() {
        return go2;
    }

    public void setGo2(Integer go2) {
        this.go2 = go2;
    }

    public Integer getBacakaGo2() {
        return bacakaGo2;
    }

    public void setBacakaGo2(Integer bacakaGo2) {
        this.bacakaGo2 = bacakaGo2;
    }

    public Integer getGo3() {
        return go3;
    }

    public void setGo3(Integer go3) {
        this.go3 = go3;
    }

    public Integer getBacakaGo3() {
        return bacakaGo3;
    }

    public void setBacakaGo3(Integer bacakaGo3) {
        this.bacakaGo3 = bacakaGo3;
    }

    public Integer getGo4() {
        return go4;
    }

    public void setGo4(Integer go4) {
        this.go4 = go4;
    }

    public Integer getBacakaGo4() {
        return bacakaGo4;
    }

    public void setBacakaGo4(Integer bacakaGo4) {
        this.bacakaGo4 = bacakaGo4;
    }

    public Integer getGo5() {
        return go5;
    }

    public void setGo5(Integer go5) {
        this.go5 = go5;
    }

    public Integer getBacakaGo5() {
        return bacakaGo5;
    }

    public void setBacakaGo5(Integer bacakaGo5) {
        this.bacakaGo5 = bacakaGo5;
    }

    public Integer getGo6() {
        return go6;
    }

    public void setGo6(Integer go6) {
        this.go6 = go6;
    }

    public Integer getBacakaGo6() {
        return bacakaGo6;
    }

    public void setBacakaGo6(Integer bacakaGo6) {
        this.bacakaGo6 = bacakaGo6;
    }

    public Integer getJth() {
        return jth;
    }

    public void setJth(Integer jth) {
        this.jth = jth;
    }

    public Integer getBacakaJth() {
        return bacakaJth;
    }

    public void setBacakaJth(Integer bacakaJth) {
        this.bacakaJth = bacakaJth;
    }

    public Integer getJtt() {
        return jtt;
    }

    public void setJtt(Integer jtt) {
        this.jtt = jtt;
    }

    public Integer getBacakaJtt() {
        return bacakaJtt;
    }

    public void setBacakaJtt(Integer bacakaJtt) {
        this.bacakaJtt = bacakaJtt;
    }

    public Integer getKet() {
        return ket;
    }

    public void setKet(Integer ket) {
        this.ket = ket;
    }

    public Integer getBacakaKet() {
        return bacakaKet;
    }

    public void setBacakaKet(Integer bacakaKet) {
        this.bacakaKet = bacakaKet;
    }

    public Integer getJk() {
        return jk;
    }

    public void setJk(Integer jk) {
        this.jk = jk;
    }

    public Integer getBacakaJk() {
        return bacakaJk;
    }

    public void setBacakaJk(Integer bacakaJk) {
        this.bacakaJk = bacakaJk;
    }

    public Integer getJv() {
        return jv;
    }

    public void setJv(Integer jv) {
        this.jv = jv;
    }

    public Integer getBacakaJv() {
        return bacakaJv;
    }

    public void setBacakaJv(Integer bacakaJv) {
        this.bacakaJv = bacakaJv;
    }

    public Integer getJah() {
        return jah;
    }

    public void setJah(Integer jah) {
        this.jah = jah;
    }

    public Integer getPakan() {
        return pakan;
    }

    public void setPakan(Integer pakan) {
        this.pakan = pakan;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getJTLog() {
        return JTLog;
    }

    public void setJTLog(String JTLog) {
        this.JTLog = JTLog;
    }

    public Integer getSemiAnnualId() {
        return semiAnnualId;
    }

    public void setSemiAnnualId(Integer semiAnnualId) {
        this.semiAnnualId = semiAnnualId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHistory that = (ClientHistory) o;
        return isPaid == that.isPaid &&
                Objects.equals(id, that.id) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(violationActNumber, that.violationActNumber) &&
                Objects.equals(visitType, that.visitType) &&
                Objects.equals(visitDescription, that.visitDescription) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(previousVisitDate, that.previousVisitDate) &&
                Objects.equals(nextVisitDate, that.nextVisitDate) &&
                Objects.equals(stampNumbers, that.stampNumbers) &&
                Objects.equals(go1, that.go1) &&
                Objects.equals(bacakaGo1, that.bacakaGo1) &&
                Objects.equals(go2, that.go2) &&
                Objects.equals(bacakaGo2, that.bacakaGo2) &&
                Objects.equals(go3, that.go3) &&
                Objects.equals(bacakaGo3, that.bacakaGo3) &&
                Objects.equals(go4, that.go4) &&
                Objects.equals(bacakaGo4, that.bacakaGo4) &&
                Objects.equals(go5, that.go5) &&
                Objects.equals(bacakaGo5, that.bacakaGo5) &&
                Objects.equals(go6, that.go6) &&
                Objects.equals(bacakaGo6, that.bacakaGo6) &&
                Objects.equals(jth, that.jth) &&
                Objects.equals(bacakaJth, that.bacakaJth) &&
                Objects.equals(jtt, that.jtt) &&
                Objects.equals(bacakaJtt, that.bacakaJtt) &&
                Objects.equals(ket, that.ket) &&
                Objects.equals(bacakaKet, that.bacakaKet) &&
                Objects.equals(jk, that.jk) &&
                Objects.equals(bacakaJk, that.bacakaJk) &&
                Objects.equals(jv, that.jv) &&
                Objects.equals(bacakaJv, that.bacakaJv) &&
                Objects.equals(jah, that.jah) &&
                Objects.equals(pakan, that.pakan) &&
                Objects.equals(risk, that.risk) &&
                Objects.equals(masterId, that.masterId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, clientId, violationActNumber, visitType, visitDescription, updateDate, previousVisitDate, nextVisitDate, stampNumbers, go1, bacakaGo1, go2, bacakaGo2, go3, bacakaGo3, go4, bacakaGo4, go5, bacakaGo5, go6, bacakaGo6, jth, bacakaJth, jtt, bacakaJtt, ket, bacakaKet, jk, bacakaJk, jv, bacakaJv, jah, pakan, risk, masterId, isPaid);
    }
}

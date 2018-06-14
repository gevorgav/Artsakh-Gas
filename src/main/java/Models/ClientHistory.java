package Models;

import java.util.Date;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class ClientHistory {

    private Integer id;

    private Client client;

    private Integer violationActNumber;

    private Date updateDate;

    private Date previousVisitDate;

    private Date nextVisitDate;

    private ViolationCode violationCode;

    private String stampNumbers;

    private Integer go;

    private Integer jth;

    private Integer jtt;

    private Integer ket;

    private Integer jah;

    private Integer jk;

    private Integer jv;

    private Integer go3;

    private Integer go2;

    private Integer risk;

    public ClientHistory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getViolationActNumber() {
        return violationActNumber;
    }

    public void setViolationActNumber(Integer violationActNumber) {
        this.violationActNumber = violationActNumber;
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

    public ViolationCode getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(ViolationCode violationCode) {
        this.violationCode = violationCode;
    }

    public String getStampNumbers() {
        return stampNumbers;
    }

    public void setStampNumbers(String stampNumbers) {
        this.stampNumbers = stampNumbers;
    }

    public Integer getGo() {
        return go;
    }

    public void setGo(Integer go) {
        this.go = go;
    }

    public Integer getJth() {
        return jth;
    }

    public void setJth(Integer jth) {
        this.jth = jth;
    }

    public Integer getJtt() {
        return jtt;
    }

    public void setJtt(Integer jtt) {
        this.jtt = jtt;
    }

    public Integer getKet() {
        return ket;
    }

    public void setKet(Integer ket) {
        this.ket = ket;
    }

    public Integer getJah() {
        return jah;
    }

    public void setJah(Integer jah) {
        this.jah = jah;
    }

    public Integer getJk() {
        return jk;
    }

    public void setJk(Integer jk) {
        this.jk = jk;
    }

    public Integer getJv() {
        return jv;
    }

    public void setJv(Integer jv) {
        this.jv = jv;
    }

    public Integer getGo3() {
        return go3;
    }

    public void setGo3(Integer go3) {
        this.go3 = go3;
    }

    public Integer getGo2() {
        return go2;
    }

    public void setGo2(Integer go2) {
        this.go2 = go2;
    }

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
        this.risk = risk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientHistory that = (ClientHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (violationActNumber != null ? !violationActNumber.equals(that.violationActNumber) : that.violationActNumber != null)
            return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (previousVisitDate != null ? !previousVisitDate.equals(that.previousVisitDate) : that.previousVisitDate != null)
            return false;
        if (nextVisitDate != null ? !nextVisitDate.equals(that.nextVisitDate) : that.nextVisitDate != null)
            return false;
        if (violationCode != null ? !violationCode.equals(that.violationCode) : that.violationCode != null)
            return false;
        if (stampNumbers != null ? !stampNumbers.equals(that.stampNumbers) : that.stampNumbers != null) return false;
        if (go != null ? !go.equals(that.go) : that.go != null) return false;
        if (jth != null ? !jth.equals(that.jth) : that.jth != null) return false;
        if (jtt != null ? !jtt.equals(that.jtt) : that.jtt != null) return false;
        if (ket != null ? !ket.equals(that.ket) : that.ket != null) return false;
        if (jah != null ? !jah.equals(that.jah) : that.jah != null) return false;
        if (jk != null ? !jk.equals(that.jk) : that.jk != null) return false;
        if (jv != null ? !jv.equals(that.jv) : that.jv != null) return false;
        if (go3 != null ? !go3.equals(that.go3) : that.go3 != null) return false;
        if (go2 != null ? !go2.equals(that.go2) : that.go2 != null) return false;
        return risk != null ? risk.equals(that.risk) : that.risk == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (violationActNumber != null ? violationActNumber.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (previousVisitDate != null ? previousVisitDate.hashCode() : 0);
        result = 31 * result + (nextVisitDate != null ? nextVisitDate.hashCode() : 0);
        result = 31 * result + (violationCode != null ? violationCode.hashCode() : 0);
        result = 31 * result + (stampNumbers != null ? stampNumbers.hashCode() : 0);
        result = 31 * result + (go != null ? go.hashCode() : 0);
        result = 31 * result + (jth != null ? jth.hashCode() : 0);
        result = 31 * result + (jtt != null ? jtt.hashCode() : 0);
        result = 31 * result + (ket != null ? ket.hashCode() : 0);
        result = 31 * result + (jah != null ? jah.hashCode() : 0);
        result = 31 * result + (jk != null ? jk.hashCode() : 0);
        result = 31 * result + (jv != null ? jv.hashCode() : 0);
        result = 31 * result + (go3 != null ? go3.hashCode() : 0);
        result = 31 * result + (go2 != null ? go2.hashCode() : 0);
        result = 31 * result + (risk != null ? risk.hashCode() : 0);
        return result;
    }
}

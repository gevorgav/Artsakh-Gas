package Models;

import java.util.Objects;

public class SemiAnnualConfig {
    private Integer semiAnnualId;
    private String semiAnnual;

    public SemiAnnualConfig() {
    }

    public Integer getSemiAnnualId() {
        return semiAnnualId;
    }

    public void setSemiAnnualId(Integer semiAnnualId) {
        this.semiAnnualId = semiAnnualId;
    }

    public String getSemiAnnual() {
        return semiAnnual;
    }

    public void setSemiAnnual(String semiAnnual) {
        this.semiAnnual = semiAnnual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemiAnnualConfig that = (SemiAnnualConfig) o;
        return  Objects.equals(semiAnnualId, that.semiAnnualId) &&
                Objects.equals(semiAnnual, that.semiAnnual);
    }

    @Override
    public int hashCode() {
        return Objects.hash( semiAnnualId, semiAnnual);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SemiAnnualConfig semiAnnualConfig = new SemiAnnualConfig();
        semiAnnualConfig.setSemiAnnualId(this.semiAnnualId);
        semiAnnualConfig.setSemiAnnual(this.semiAnnual);
        return semiAnnualConfig;
    }

    @Override
    public String toString() {
        return "SemiAnnualConfig{" +
                ", semiAnnualId=" + semiAnnualId +
                ", semiAnnual='" + semiAnnual + '\'' +
                '}';
    }
}

package Models;

import java.util.Objects;

public class SemiAnnualRegion {
    private Integer id;
    private Integer semiAnnualId;
    private Integer regionId;
    private String region;
    private String semiAnnual;

    public SemiAnnualRegion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemiAnnualId() {
        return semiAnnualId;
    }

    public void setSemiAnnualId(Integer semiAnnualId) {
        this.semiAnnualId = semiAnnualId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
        SemiAnnualRegion that = (SemiAnnualRegion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(semiAnnualId, that.semiAnnualId) &&
                Objects.equals(regionId, that.regionId) &&
                Objects.equals(region, that.region) &&
                Objects.equals(semiAnnual, that.semiAnnual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semiAnnualId, regionId, region, semiAnnual);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SemiAnnualRegion semiAnnualRegion = new SemiAnnualRegion();
        semiAnnualRegion.setId(this.id);
        semiAnnualRegion.setRegionId(this.regionId);
        semiAnnualRegion.setSemiAnnualId(this.semiAnnualId);
        semiAnnualRegion.setSemiAnnual(this.semiAnnual);
        semiAnnualRegion.setRegion(this.region);
        return semiAnnualRegion;
    }

    @Override
    public String toString() {
        return "SemiAnnualRegion{" +
                "id=" + id +
                ", semiAnnualId=" + semiAnnualId +
                ", regionId=" + regionId +
                ", region='" + region + '\'' +
                ", semiAnnual='" + semiAnnual + '\'' +
                '}';
    }
}

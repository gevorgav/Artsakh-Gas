package Models;

import java.util.Arrays;

/**
 * Created by astghik.mamunc on 7/24/2018.
 */
public class Filter {

    private Integer regionId;

    private Integer cityId;

    private Integer streetId;

    private Integer ashtId;

    private Integer grpId;

    private Integer sectionId;

    private Integer subSectionId;

    private String[] violationCodes;

    private boolean counterNumber;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Integer getAshtId() {
        return ashtId;
    }

    public void setAshtId(Integer ashtId) {
        this.ashtId = ashtId;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSubSectionId() {
        return subSectionId;
    }

    public void setSubSectionId(Integer subSectionId) {
        this.subSectionId = subSectionId;
    }

    public String[] getViolationCodes() {
        return violationCodes;
    }

    public void setViolationCodes(String[] violationCodes) {
        this.violationCodes = violationCodes;
    }

    public boolean isCounterNumber() {
        return counterNumber;
    }

    public void setCounterNumber(boolean counterNumber) {
        this.counterNumber = counterNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (counterNumber != filter.counterNumber) return false;
        if (regionId != null ? !regionId.equals(filter.regionId) : filter.regionId != null) return false;
        if (cityId != null ? !cityId.equals(filter.cityId) : filter.cityId != null) return false;
        if (streetId != null ? !streetId.equals(filter.streetId) : filter.streetId != null) return false;
        if (ashtId != null ? !ashtId.equals(filter.ashtId) : filter.ashtId != null) return false;
        if (grpId != null ? !grpId.equals(filter.grpId) : filter.grpId != null) return false;
        if (sectionId != null ? !sectionId.equals(filter.sectionId) : filter.sectionId != null) return false;
        if (subSectionId != null ? !subSectionId.equals(filter.subSectionId) : filter.subSectionId != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(violationCodes, filter.violationCodes);
    }

    @Override
    public int hashCode() {
        int result = regionId != null ? regionId.hashCode() : 0;
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (streetId != null ? streetId.hashCode() : 0);
        result = 31 * result + (ashtId != null ? ashtId.hashCode() : 0);
        result = 31 * result + (grpId != null ? grpId.hashCode() : 0);
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (subSectionId != null ? subSectionId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(violationCodes);
        result = 31 * result + (counterNumber ? 1 : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Filter filter = new Filter();
        filter.setAshtId(this.ashtId);
        filter.setCityId(this.cityId);
        filter.setGrpId(this.grpId);
        filter.setRegionId(this.regionId);
        filter.setSectionId(this.sectionId);
        filter.setStreetId(this.streetId);
        filter.setSubSectionId(this.subSectionId);
        filter.setViolationCodes(this.violationCodes);
        filter.setCounterNumber(this.counterNumber);
        return filter;
    }
}

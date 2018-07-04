package Models;

import Core.Models.City;
import Core.Models.Region;

import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class Client {

    private String id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String counterNumber;

    private Integer cityId;

    private Integer regionId;

    private Integer streetId;

    private Integer homeNumber;

    private Integer apartmentNumber;

    private Integer ashtId;

    private Integer grpId;

    private Integer sectionId;

    private Integer subSectionId;

    private Integer typeId;

    private String typeNumber;

    private Integer grsId;

    private ClientHistory clientHistory;

    private Region region;

    private boolean isNew;

    public Client() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCounterNumber() {
        return counterNumber;
    }

    public void setCounterNumber(String counterNumber) {
        this.counterNumber = counterNumber;
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

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
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

    public ClientHistory getClientHistory() {
        return clientHistory;
    }

    public void setClientHistory(ClientHistory clientHistory) {
        this.clientHistory = clientHistory;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(String typeNumber) {
        this.typeNumber = typeNumber;
    }

    public Integer getGrsId() {
        return grsId;
    }

    public void setGrsId(Integer grsId) {
        this.grsId = grsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (middleName != null ? !middleName.equals(client.middleName) : client.middleName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(client.phoneNumber) : client.phoneNumber != null) return false;
        if (counterNumber != null ? !counterNumber.equals(client.counterNumber) : client.counterNumber != null)
            return false;
        if (cityId != null ? !cityId.equals(client.cityId) : client.cityId != null) return false;
        if (regionId != null ? !regionId.equals(client.regionId) : client.regionId != null) return false;
        if (streetId != null ? !streetId.equals(client.streetId) : client.streetId != null) return false;
        if (sectionId != null ? !sectionId.equals(client.sectionId) : client.sectionId != null) return false;
        if (subSectionId != null ? !subSectionId.equals(client.subSectionId) : client.subSectionId != null) return false;
        if (homeNumber != null ? !homeNumber.equals(client.homeNumber) : client.homeNumber != null) return false;
        if (apartmentNumber != null ? !apartmentNumber.equals(client.apartmentNumber) : client.apartmentNumber != null)
            return false;
        if (ashtId != null ? !ashtId.equals(client.ashtId) : client.ashtId != null) return false;
        if (grpId != null ? !grpId.equals(client.grpId) : client.grpId != null) return false;
        if (grsId != null ? !grsId.equals(client.grsId) : client.grsId != null) return false;
        if (typeId != null ? !typeId.equals(client.typeId) : client.typeId != null) return false;
        if (typeNumber != null ? !typeNumber.equals(client.typeNumber) : client.typeNumber != null) return false;
        return clientHistory != null ? clientHistory.equals(client.clientHistory) : client.clientHistory == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (counterNumber != null ? counterNumber.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (streetId != null ? streetId.hashCode() : 0);
        result = 31 * result + (homeNumber != null ? homeNumber.hashCode() : 0);
        result = 31 * result + (apartmentNumber != null ? apartmentNumber.hashCode() : 0);
        result = 31 * result + (ashtId != null ? ashtId.hashCode() : 0);
        result = 31 * result + (grpId != null ? grpId.hashCode() : 0);
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (subSectionId != null ? subSectionId.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (clientHistory != null ? clientHistory.hashCode() : 0);
        result = 31 * result + (typeNumber != null ? typeNumber.hashCode() : 0);
        result = 31 * result + (grsId != null ? grsId.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Client client = new Client();
        client.setId(this.id);
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setMiddleName(this.middleName);
        client.setPhoneNumber(this.phoneNumber);
        client.setCounterNumber(this.counterNumber);
        client.setRegionId(this.regionId);
        client.setCityId(this.cityId);
        client.setStreetId(this.streetId);
        client.setHomeNumber(this.homeNumber);
        client.setApartmentNumber(this.apartmentNumber);
        client.setAshtId(this.ashtId);
        client.setGrpId(this.grpId);
        client.setGrsId(this.grsId);
        client.setSectionId(this.sectionId);
        client.setSubSectionId(this.subSectionId);
        client.setTypeId(this.typeId);
        client.setTypeNumber(this.typeNumber);
        client.setClientHistory(this.clientHistory);
        return client;
    }
}

package Models;

import Core.Models.City;

import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class Client {

    private Integer id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String counterNumber;

    private Integer regionId;

    private Integer cityId;

    private Integer streetId;

    private Integer homeNumber;

    private Integer apartmentNumber;

    private Integer ashtId;

    private Integer grpId;

    private ClientHistory clientHistory;

    public Client() {
    }

    public Client(Integer id, String firstName, String lastName, String middleName, String phoneNumber, String counterNumber, Integer regionId, Integer cityId, Integer streetId, Integer homeNumber, Integer apartmentNumber, Integer ashtId, Integer grpId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.counterNumber = counterNumber;
        this.regionId = regionId;
        this.cityId = cityId;
        this.streetId = streetId;
        this.homeNumber = homeNumber;
        this.apartmentNumber = apartmentNumber;
        this.ashtId = ashtId;
        this.grpId = grpId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, phoneNumber, counterNumber, regionId, cityId, streetId, homeNumber, apartmentNumber, ashtId, grpId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Client)) return false;

        Client client = (Client) obj;

        return Objects.equals(client.id, this.id)
                && Objects.equals(client.firstName, this.firstName) && Objects.equals(client.lastName, this.lastName)
                && Objects.equals(client.middleName, this.middleName) && Objects.equals(client.phoneNumber, this.phoneNumber)
                && Objects.equals(client.counterNumber, this.counterNumber) && Objects.equals(client.regionId, this.regionId)
                && Objects.equals(client.cityId, this.cityId) && Objects.equals(client.streetId, this.streetId)
                && Objects.equals(client.homeNumber, this.homeNumber) && Objects.equals(client.apartmentNumber, this.apartmentNumber)
                && Objects.equals(client.ashtId, this.ashtId) && Objects.equals(client.grpId, this.grpId);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.middleName;
    }
}

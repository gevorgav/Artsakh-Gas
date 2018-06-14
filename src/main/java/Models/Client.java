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

    private City city;

    private Street street;

    private Integer homeNumber;

    private Integer apartmentNumber;

    private Integer ashtId;

    private GRP grp;

    private ClientHistory clientHistory;

    public Client() {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
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

    public GRP getGrp() {
        return grp;
    }

    public void setGrp(GRP grp) {
        this.grp = grp;
    }

    public ClientHistory getClientHistory() {
        return clientHistory;
    }

    public void setClientHistory(ClientHistory clientHistory) {
        this.clientHistory = clientHistory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, phoneNumber, counterNumber, city, street, homeNumber, apartmentNumber, ashtId, grp, clientHistory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Client)) return false;

        Client client = (Client) obj;

        return Objects.equals(client.id, this.id)
                && Objects.equals(client.firstName, this.firstName) && Objects.equals(client.lastName, this.lastName)
                && Objects.equals(client.middleName, this.middleName) && Objects.equals(client.phoneNumber, this.phoneNumber)
                && Objects.equals(client.counterNumber, this.counterNumber) && Objects.equals(client.city, this.city)
                && Objects.equals(client.street, this.street) && Objects.equals(client.homeNumber, this.homeNumber)
                && Objects.equals(client.apartmentNumber, this.apartmentNumber) && Objects.equals(client.ashtId, this.ashtId)
                && Objects.equals(client.grp, this.grp) && Objects.equals(client.clientHistory, this.clientHistory);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.middleName;
    }
}

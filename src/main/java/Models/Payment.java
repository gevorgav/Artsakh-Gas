package Models;

import java.util.Objects;
/**
 * Created by gevorg.avetisyan on 6/13/2018.
 */
public class Payment {
    private Integer id;
    private String clientId;
    private Integer clientHistoryTmpId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer regionId;
    private Integer cityId;
    private Integer streetId;
    private Integer semiAnnualId;
    private Double debt;
    private Double balance;
    private Double pay;

    public Payment() {
    }

    public Payment(Integer id, String clientId, Integer clientHistoryTmpId, String firstName, String lastName, String middleName, Integer regionId, Integer cityId, Integer streetId, Integer semiAnnualId, Double debt, Double pay) {
        this.id = id;
        this.clientId = clientId;
        this.clientHistoryTmpId = clientHistoryTmpId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.regionId = regionId;
        this.cityId = cityId;
        this.streetId = streetId;
        this.semiAnnualId = semiAnnualId;
        this.debt = debt;
        this.pay = pay;
    }

    public Payment(String clientId, Integer clientHistoryTmpId, String firstName, String lastName, String middleName, Integer regionId, Integer cityId, Integer streetId, Integer semiAnnualId, Double debt, Double pay) {
        this.clientId = clientId;
        this.clientHistoryTmpId = clientHistoryTmpId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.regionId = regionId;
        this.cityId = cityId;
        this.streetId = streetId;
        this.semiAnnualId = semiAnnualId;
        this.debt = debt;
        this.pay = pay;
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

    public Integer getClientHistoryTmpId() {
        return clientHistoryTmpId;
    }

    public void setClientHistoryTmpId(Integer clientHistoryTmpId) {
        this.clientHistoryTmpId = clientHistoryTmpId;
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

    public Integer getSemiAnnualId() {
        return semiAnnualId;
    }

    public void setSemiAnnualId(Integer semiAnnualId) {
        this.semiAnnualId = semiAnnualId;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(clientId, payment.clientId) &&
                Objects.equals(clientHistoryTmpId, payment.clientHistoryTmpId) &&
                Objects.equals(firstName, payment.firstName) &&
                Objects.equals(lastName, payment.lastName) &&
                Objects.equals(middleName, payment.middleName) &&
                Objects.equals(regionId, payment.regionId) &&
                Objects.equals(cityId, payment.cityId) &&
                Objects.equals(streetId, payment.streetId) &&
                Objects.equals(semiAnnualId, payment.semiAnnualId) &&
                Objects.equals(debt, payment.debt) &&
                Objects.equals(balance, payment.balance) &&
                Objects.equals(pay, payment.pay);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, clientId, clientHistoryTmpId, firstName, lastName, middleName, regionId, cityId, streetId, semiAnnualId, debt, balance, pay);
    }
}

package Models;

import java.util.Date;
import java.util.Objects;

/**
 * Created by gevorg.avetisyan on 6/13/2018.
 */
public class Payment {
    private Integer id;
    private String clientId;
    private Integer clientHistoryTmpId;
    private String fullName;
    private Integer regionId;
    private String city;
    private String street;
    private String home;
    private Integer semiAnnualId;
    private Double debt;
    private Double balance;
    private Double pay;
    private Date updatedDate;
    private Integer bankId;
    private Integer userId;

    public Payment() {
    }

    public Payment(Integer id, String clientId, Integer clientHistoryTmpId, String fullName, Integer regionId, String city, String street, String home, Integer semiAnnualId, Double debt, Double pay) {
        this.id = id;
        this.clientId = clientId;
        this.clientHistoryTmpId = clientHistoryTmpId;
        this.fullName = fullName;
        this.regionId = regionId;
        this.city = city;
        this.street = street;
        this.home = home;
        this.semiAnnualId = semiAnnualId;
        this.debt = debt;
        this.pay = pay;
    }

    public Payment(String clientId, Integer clientHistoryTmpId, String fullName, Integer regionId, String city, String street, String home, Integer semiAnnualId, Double debt, Double pay, Integer userId, Date updatedDate) {
        this.clientId = clientId;
        this.clientHistoryTmpId = clientHistoryTmpId;
        this.fullName = fullName;
        this.regionId = regionId;
        this.city = city;
        this.street = street;
        this.home = home;
        this.semiAnnualId = semiAnnualId;
        this.debt = debt;
        this.pay = pay;
        this.userId = userId;
        this.updatedDate = updatedDate;
    }

    public Payment(Integer id, String clientId, Integer clientHistoryTmpId, String fullName, Integer regionId, String city, String street, String home, Integer semiAnnualId, Double debt, Double pay, Date updatedDate, Integer bankId, Integer userId) {
        this.id = id;
        this.clientId = clientId;
        this.clientHistoryTmpId = clientHistoryTmpId;
        this.fullName = fullName;
        this.regionId = regionId;
        this.city = city;
        this.street = street;
        this.home = home;
        this.semiAnnualId = semiAnnualId;
        this.debt = debt;
        this.pay = pay;
        this.updatedDate = updatedDate;
        this.bankId = bankId;
        this.userId = userId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(clientId, payment.clientId) &&
                Objects.equals(clientHistoryTmpId, payment.clientHistoryTmpId) &&
                Objects.equals(fullName, payment.fullName) &&
                Objects.equals(regionId, payment.regionId) &&
                Objects.equals(city, payment.city) &&
                Objects.equals(street, payment.street) &&
                Objects.equals(home, payment.home) &&
                Objects.equals(semiAnnualId, payment.semiAnnualId) &&
                Objects.equals(debt, payment.debt) &&
                Objects.equals(balance, payment.balance) &&
                Objects.equals(pay, payment.pay);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, clientId, clientHistoryTmpId, fullName, regionId, city, street, home, semiAnnualId, debt, balance, pay);
    }
}

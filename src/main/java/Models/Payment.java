package Models;

import java.util.Objects;

public class Payment {
    private Integer id;
    private String clientId;
    private Integer clientHistryTmpId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Double balance;
    private Double pay;

    public Payment() {
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

    public Integer getClientHistryTmpId() {
        return clientHistryTmpId;
    }

    public void setClientHistryTmpId(Integer clientHistryTmpId) {
        this.clientHistryTmpId = clientHistryTmpId;
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
                Objects.equals(clientHistryTmpId, payment.clientHistryTmpId) &&
                Objects.equals(firstName, payment.firstName) &&
                Objects.equals(lastName, payment.lastName) &&
                Objects.equals(middleName, payment.middleName) &&
                Objects.equals(balance, payment.balance) &&
                Objects.equals(pay, payment.pay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, clientHistryTmpId, firstName, lastName, middleName, balance, pay);
    }
}

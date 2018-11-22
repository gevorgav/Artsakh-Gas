package Models;

import Core.Models.Region;

import java.math.BigDecimal;

/**
 * Created by astghik.mamunc on 11/18/2018.
 */
public class PaymentReport {

	private Integer regionId;

	private Integer bankId;

	private BigDecimal pay;


	private Region region;

	private Bank bank;

	public PaymentReport(Integer regionId, Integer bankId, BigDecimal pay) {
		this.regionId = regionId;
		this.bankId = bankId;
		this.pay = pay;
	}

	public PaymentReport() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public BigDecimal getPay() {
		return pay;
	}

	public void setPay(BigDecimal pay) {
		this.pay = pay;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PaymentReport that = (PaymentReport) o;

		if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;
		if (bankId != null ? !bankId.equals(that.bankId) : that.bankId != null) return false;
		return pay != null ? pay.equals(that.pay) : that.pay == null;
	}

	@Override
	public int hashCode() {
		int result = regionId != null ? regionId.hashCode() : 0;
		result = 31 * result + (bankId != null ? bankId.hashCode() : 0);
		result = 31 * result + (pay != null ? pay.hashCode() : 0);
		return result;
	}
}

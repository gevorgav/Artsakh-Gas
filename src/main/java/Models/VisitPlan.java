package Models;

import Core.Models.Month;

/**
 * Created by astghik.mamunc on 8/12/2018.
 */
public class VisitPlan {

	private Integer id;

	private Integer sectionId;

	private Integer monthId;

	private Integer plannedClients;

	private Integer plannedCompanies;

	private Integer semiAnnualId;

	private Section section;

	private Month month;

	public VisitPlan() {
	}

	public VisitPlan(Integer sectionId, Integer monthId, Integer plannedClients, Integer plannedCompanies, Integer semiAnnualId, Section section, Month month) {
		this.id = id;
		this.sectionId = sectionId;
		this.monthId = monthId;
		this.plannedClients = plannedClients;
		this.plannedCompanies = plannedCompanies;
		this.semiAnnualId = semiAnnualId;
		this.section = section;
		this.month = month;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public Integer getPlannedClients() {
		return plannedClients;
	}

	public void setPlannedClients(Integer plannedClients) {
		this.plannedClients = plannedClients;
	}

	public Integer getPlannedCompanies() {
		return plannedCompanies;
	}

	public void setPlannedCompanies(Integer plannedCompanies) {
		this.plannedCompanies = plannedCompanies;
	}

	public Integer getSemiAnnualId() {
		return semiAnnualId;
	}

	public void setSemiAnnualId(Integer semiAnnualId) {
		this.semiAnnualId = semiAnnualId;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		VisitPlan visitPlan = (VisitPlan) o;

		if (id != null ? !id.equals(visitPlan.id) : visitPlan.id != null) return false;
		if (sectionId != null ? !sectionId.equals(visitPlan.sectionId) : visitPlan.sectionId != null) return false;
		if (monthId != null ? !monthId.equals(visitPlan.monthId) : visitPlan.monthId != null) return false;
		if (plannedClients != null ? !plannedClients.equals(visitPlan.plannedClients) : visitPlan.plannedClients != null)
			return false;
		if (plannedCompanies != null ? !plannedCompanies.equals(visitPlan.plannedCompanies) : visitPlan.plannedCompanies != null)
			return false;
		if (semiAnnualId != null ? !semiAnnualId.equals(visitPlan.semiAnnualId) : visitPlan.semiAnnualId != null)
			return false;
		if (section != null ? !section.equals(visitPlan.section) : visitPlan.section != null) return false;
		return month != null ? month.equals(visitPlan.month) : visitPlan.month == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
		result = 31 * result + (monthId != null ? monthId.hashCode() : 0);
		result = 31 * result + (plannedClients != null ? plannedClients.hashCode() : 0);
		result = 31 * result + (plannedCompanies != null ? plannedCompanies.hashCode() : 0);
		result = 31 * result + (semiAnnualId != null ? semiAnnualId.hashCode() : 0);
		result = 31 * result + (section != null ? section.hashCode() : 0);
		result = 31 * result + (month != null ? month.hashCode() : 0);
		return result;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		VisitPlan visitPlan = new VisitPlan();
		visitPlan.setId(this.id);
		visitPlan.setSectionId(this.sectionId);
		visitPlan.setMonthId(this.monthId);
		visitPlan.setPlannedClients(this.plannedClients);
		visitPlan.setPlannedCompanies(this.plannedCompanies);
		visitPlan.setSemiAnnualId(this.semiAnnualId);
		visitPlan.setSection(this.section);
		visitPlan.setMonth(this.month);
		return visitPlan;
	}
}

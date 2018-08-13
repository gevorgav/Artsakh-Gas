package Models;

import Core.Models.Month;

/**
 * Created by astghik.mamunc on 8/12/2018.
 */
public class VisitPlan {

	private Integer id;

	private Integer sectionId;

	private Integer monthId;

	private Integer planned;

	private Integer semiAnnualId;

	private Section section;

	private Month month;

	public VisitPlan() {
	}

	public VisitPlan(Integer sectionId, Integer monthId, Integer planned, Integer semiAnnualId, Section section, Month month) {
		this.sectionId = sectionId;
		this.monthId = monthId;
		this.planned = planned;
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

	public Integer getPlanned() {
		return planned;
	}

	public void setPlanned(Integer planned) {
		this.planned = planned;
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
		if (planned != null ? !planned.equals(visitPlan.planned) : visitPlan.planned != null) return false;
		return semiAnnualId != null ? semiAnnualId.equals(visitPlan.semiAnnualId) : visitPlan.semiAnnualId == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
		result = 31 * result + (monthId != null ? monthId.hashCode() : 0);
		result = 31 * result + (planned != null ? planned.hashCode() : 0);
		result = 31 * result + (semiAnnualId != null ? semiAnnualId.hashCode() : 0);
		return result;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		VisitPlan visitPlan = new VisitPlan();
		visitPlan.setId(this.id);
		visitPlan.setSectionId(this.sectionId);
		visitPlan.setMonthId(this.monthId);
		visitPlan.setPlanned(this.planned);
		visitPlan.setSemiAnnualId(this.semiAnnualId);
		visitPlan.setSection(this.section);
		visitPlan.setMonth(this.month);
		return visitPlan;
	}
}

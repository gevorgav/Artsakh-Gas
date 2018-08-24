package dao.mapper;

import Models.VisitPlan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 8/12/2018.
 */
public class VisitPlanMapper implements RowMapper<VisitPlan> {
	@Override
	public VisitPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
		VisitPlan visitPlan = new VisitPlan();
		visitPlan.setId(rs.getInt("id"));
		visitPlan.setSectionId(rs.getInt("sectionId"));
		visitPlan.setMonthId(rs.getInt("monthId"));
		visitPlan.setPlannedCompanies(rs.getInt("plannedCompanies"));
		visitPlan.setPlannedClients(rs.getInt("plannedClients"));
		visitPlan.setSemiAnnualId(rs.getInt("semiAnnualId"));
		return visitPlan;
	}
}

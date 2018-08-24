package dao;

import Models.VisitPlan;
import dao.mapper.VisitPlanMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 8/12/2018.
 */
@Repository
public class VisitPlanDao extends Dao<VisitPlan> {
	@Override
	public List<VisitPlan> loadAll() {
		try {
			String sql = "SELECT * FROM visitPlan";
			return jdbcTemplate.query(sql, new VisitPlanMapper());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public VisitPlan loadById(Integer id) {
		Objects.requireNonNull(id);
		String sql = "SELECT * FROM visitPlan WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new VisitPlanMapper(), id);
	}

	@Override
	public boolean insert(VisitPlan visitPlan) {
		Objects.requireNonNull(visitPlan);
		String sql = "INSERT INTO visitPlan(sectionId, plannedClients, plannedCompanies, monthId, semiAnnualId) VALUES (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, visitPlan.getSectionId(), visitPlan.getPlannedClients(), visitPlan.getPlannedCompanies(), visitPlan.getMonthId(), visitPlan.getSemiAnnualId());
		return result == 1;
	}

	@Override
	public boolean update(VisitPlan visitPlan) {
		Objects.requireNonNull(visitPlan);
		String sql = "UPDATE visitPlan SET sectionId = ?, plannedClients = ?, plannedCompanies = ?, monthId = ?, semiAnnualId = ? WHERE id = ?";
		int result = jdbcTemplate.update(sql, visitPlan.getSectionId(), visitPlan.getPlannedClients(), visitPlan.getPlannedCompanies(), visitPlan.getMonthId(), visitPlan.getSemiAnnualId(), visitPlan.getId());
		return result == 1;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	public List<VisitPlan> loadAllBySectionId(Integer sectionId) {
		try {
			String sql = "SELECT * FROM visitPlan WHERE sectionId = ?";
			return jdbcTemplate.query(sql, new VisitPlanMapper(), sectionId);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	public Integer insertAndReturnId(VisitPlan visitPlan) {
		Objects.requireNonNull(visitPlan);
		String sql = "INSERT INTO visitPlan(sectionId, plannedClients, plannedCompanies, monthId, semiAnnualId)\n" +
			"VALUES (:sectionId, :plannedClients, :plannedCompanies, :monthId, :semiAnnualId)";
		SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(visitPlan);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = namedJdbc.update(sql, fileParameters, keyHolder);
		visitPlan.setId(keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	public Integer sumPlannedByRegion(Integer regionId, Integer monthId){
		String sql = "SELECT SUM(plannedClients) FROM visitPlan WHERE monthId = ? AND sectionId in (SELECT section.id FROM section WHERE regionId = ?)";
		return jdbcTemplate.queryForObject(sql, Integer.class, monthId, regionId);
	}

	public Integer sumPlannedByRegionAndSemiAnnual(Integer regionId, Integer semiAnnualId){
		String sql = "SELECT SUM(plannedClients) FROM visitPlan WHERE semiAnnualId = ? AND sectionId in (SELECT section.id FROM section WHERE regionId = ?)";
		return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId);
	}

	public Integer sumPlannedBySection(Integer sectionId, Integer monthId){
		String sql = "SELECT SUM(plannedClients) FROM visitPlan WHERE monthId = ? AND sectionId = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, monthId, sectionId);
	}

	public Integer sumPlannedBySectionAndSemiAnnual(Integer sectionId, Integer semiAnnualId){
		String sql = "SELECT SUM(plannedClients) FROM visitPlan WHERE semiAnnualId = ? AND sectionId = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, sectionId);
	}

	public Integer sumPlannedCompaniesByRegion(Integer regionId, Integer monthId){
		String sql = "SELECT SUM(plannedCompanies) FROM visitPlan WHERE monthId = ? AND sectionId in (SELECT section.id FROM section WHERE regionId = ?)";
		return jdbcTemplate.queryForObject(sql, Integer.class, monthId, regionId);
	}

	public Integer sumPlannedCompaniesByRegionAndSemiAnnual(Integer regionId, Integer semiAnnualId){
		String sql = "SELECT SUM(plannedCompanies) FROM visitPlan WHERE semiAnnualId = ? AND sectionId in (SELECT section.id FROM section WHERE regionId = ?)";
		return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId);
	}

	public Integer sumPlannedCompaniesBySection(Integer sectionId, Integer monthId){
		String sql = "SELECT SUM(plannedCompanies) FROM visitPlan WHERE monthId = ? AND sectionId = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, monthId, sectionId);
	}

	public Integer sumPlannedCompaniesBySectionAndSemiAnnual(Integer sectionId, Integer semiAnnualId){
		String sql = "SELECT SUM(plannedCompanies) FROM visitPlan WHERE semiAnnualId = ? AND sectionId = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, sectionId);
	}

}

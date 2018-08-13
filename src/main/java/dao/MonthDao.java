package dao;

import Core.Models.Month;
import dao.mapper.MonthMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 8/12/2018.
 */
@Repository
public class MonthDao extends Dao<Month> {
	@Override
	public List<Month> loadAll() {
		try {
			String sql = "SELECT * FROM month";
			return jdbcTemplate.query(
				sql, new MonthMapper());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Month loadById(Integer id) {
		Objects.requireNonNull(id);
		String sql = "SELECT * FROM month WHERE id = ?;";
		return jdbcTemplate.queryForObject(sql, new MonthMapper(), id);
	}

	@Override
	public boolean insert(Month month) {
		return false;
	}

	@Override
	public boolean update(Month month) {
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	public List<Month> loadBySemiAnnualId(Integer semiAnnualId) {
		String sql = "SELECT * FROM month WHERE semiAnnualId  = ?";
		return jdbcTemplate.query(
			sql, new MonthMapper(), semiAnnualId);
	}
}

package dao.mapper;

import Core.Models.Month;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 8/12/2018.
 */
public class MonthMapper implements RowMapper<Month> {

	@Override
	public Month mapRow(ResultSet resultSet, int i) throws SQLException {
		Month month = new Month();
		month.setId(resultSet.getInt("id"));
		month.setName(resultSet.getString("name"));
		month.setSemiAnnualId(resultSet.getInt("semiAnnualId"));
		return month;
	}
}

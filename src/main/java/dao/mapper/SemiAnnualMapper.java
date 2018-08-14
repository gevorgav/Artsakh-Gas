package dao.mapper;

import Models.SemiAnnual;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SemiAnnualMapper implements RowMapper<SemiAnnual> {
    @Override
    public SemiAnnual mapRow(ResultSet resultSet, int i) throws SQLException {
        SemiAnnual semiAnnual = new SemiAnnual();
        semiAnnual.setId(resultSet.getInt("id"));
        semiAnnual.setName(resultSet.getString("name"));
        return semiAnnual;
    }
}

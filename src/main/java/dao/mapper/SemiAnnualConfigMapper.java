package dao.mapper;

import Models.SemiAnnualConfig;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SemiAnnualConfigMapper implements RowMapper<SemiAnnualConfig> {

    @Override
    public SemiAnnualConfig mapRow(ResultSet resultSet, int i) throws SQLException {
        SemiAnnualConfig semiAnnual = new SemiAnnualConfig();
        semiAnnual.setSemiAnnualId(resultSet.getInt("semiAnnualId"));
        semiAnnual.setSemiAnnual(resultSet.getString("semiAnnualName"));
        return semiAnnual;
    }
}

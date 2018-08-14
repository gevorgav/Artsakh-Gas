package dao.mapper;

import Models.SemiAnnualRegion;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SemiAnnualRegionMapper implements RowMapper<SemiAnnualRegion> {

    @Override
    public SemiAnnualRegion mapRow(ResultSet resultSet, int i) throws SQLException {
        SemiAnnualRegion semiAnnual = new SemiAnnualRegion();
        semiAnnual.setId(resultSet.getInt("id"));
        semiAnnual.setRegionId(resultSet.getInt("regionId"));
        semiAnnual.setSemiAnnualId(resultSet.getInt("semiAnnualId"));
        semiAnnual.setRegion(resultSet.getString("regionName"));
        semiAnnual.setSemiAnnual(resultSet.getString("semiAnnualName"));
        return semiAnnual;
    }
}

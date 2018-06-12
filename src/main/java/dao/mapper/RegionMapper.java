package dao.mapper;
import Core.Models.Region;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionMapper implements RowMapper<Region> {
    @Override
    public Region mapRow(ResultSet resultSet, int i) throws SQLException {
        Region region = new Region();
        region.setId(resultSet.getInt("id"));
        region.setName(resultSet.getString("name"));
        return region;
    }
}

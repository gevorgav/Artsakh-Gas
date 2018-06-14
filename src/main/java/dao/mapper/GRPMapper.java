package dao.mapper;

import Models.GRP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class GRPMapper implements RowMapper<GRP> {
    @Override
    public GRP mapRow(ResultSet rs, int rowNum) throws SQLException {
        GRP grp = new GRP();
        grp.setId(rs.getInt("id"));
        grp.setName(rs.getString("name"));
        grp.setCityId(rs.getObject("cityId") != null ? rs.getInt("cityId") : null);
        return grp;
    }
}

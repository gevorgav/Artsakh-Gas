package dao.mapper;

import Models.GRS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
public class GRSMapper implements RowMapper<GRS> {
    @Override
    public GRS mapRow(ResultSet rs, int rowNum) throws SQLException {
        GRS grs = new GRS();
        grs.setId(rs.getInt("id"));
        grs.setName(rs.getString("name"));
        return grs;
    }
}

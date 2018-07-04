package dao.mapper;

import Models.Asht;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
public class AshtMapper implements RowMapper<Asht> {
    @Override
    public Asht mapRow(ResultSet rs, int rowNum) throws SQLException {
        Asht asht = new Asht();
        asht.setId(rs.getInt("id"));
        asht.setName(rs.getString("name"));
        asht.setRegionId(rs.getInt("regionid"));
        return asht;
    }
}

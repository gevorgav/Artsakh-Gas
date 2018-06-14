package dao.mapper;

import Models.ViolationCode;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/14/2018.
 */
public class ViolationCodeMapper  implements RowMapper<ViolationCode> {
    @Override
    public ViolationCode mapRow(ResultSet rs, int rowNum) throws SQLException {
        ViolationCode violationCode = new ViolationCode();
        violationCode.setId(rs.getInt("id"));
        violationCode.setName(rs.getString("name"));
        violationCode.setDescription(rs.getString("description"));
        return violationCode;
    }
}

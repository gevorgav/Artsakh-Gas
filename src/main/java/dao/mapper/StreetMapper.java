package dao.mapper;

import Models.Street;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/14/2018.
 */
public class StreetMapper  implements RowMapper<Street> {
    @Override
    public Street mapRow(ResultSet rs, int rowNum) throws SQLException {
        Street street = new Street();
        street.setId(rs.getInt("id"));
        street.setName(rs.getString("name"));
        street.setCityId(rs.getInt("cityId"));
        return street;
    }
}

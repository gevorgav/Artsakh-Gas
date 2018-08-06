package dao.mapper;

import Models.Section;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/27/2018.
 */
public class SectionMapper  implements RowMapper<Section> {
    @Override
    public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
        Section section = new Section();
        section.setId(rs.getInt("id"));
        section.setName(rs.getString("name"));
        section.setRegionId(rs.getInt("regionId"));
        return section;
    }
}

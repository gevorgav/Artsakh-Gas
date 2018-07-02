package dao.mapper;

import Models.SubSection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/27/2018.
 */
public class SubSectionMapper implements RowMapper<SubSection> {
    @Override
    public SubSection mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubSection subSection = new SubSection();
        subSection.setId(rs.getInt("id"));
        subSection.setName(rs.getString("name"));
        subSection.setSectionId(rs.getInt("sectionId"));
        return subSection;
    }
}

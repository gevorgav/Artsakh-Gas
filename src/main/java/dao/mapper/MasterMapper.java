package dao.mapper;

import Models.Master;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class MasterMapper  implements RowMapper<Master> {
    @Override
    public Master mapRow(ResultSet rs, int rowNum) throws SQLException {
        Master master = new Master();
        master.setId(rs.getInt("id"));
        master.setFirstName(rs.getString("firstName"));
        master.setLastName(rs.getString("lastName"));
        master.setAshtId(rs.getObject("ashtId") != null ? rs.getInt("ashtId") : null);
        master.setSubSectionId(rs.getObject("subSectionId") != null ? rs.getInt("subSectionId") : null);
        master.setRegionId(rs.getObject("regionId") != null ? rs.getInt("regionId") : null);
        master.setSectionId(rs.getObject("sectionId") != null ? rs.getInt("sectionId") : null);
        return master;
    }
}

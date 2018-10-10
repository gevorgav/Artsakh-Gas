package dao.mapper;

import Models.Locksmith;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 7/27/2018.
 */
public class LocksmithMapper  implements RowMapper<Locksmith> {
    @Override
    public Locksmith mapRow(ResultSet rs, int rowNum) throws SQLException {
        Locksmith locksmith = new Locksmith();
        locksmith.setId(rs.getInt("id"));
        locksmith.setFirstName(rs.getString("firstName"));
        locksmith.setLastName(rs.getString("lastName"));
        locksmith.setAshtId(rs.getObject("ashtId") != null ? rs.getInt("ashtId") : null);
        locksmith.setRegionId(rs.getObject("regionId") != null ? rs.getInt("regionId") : null);
        locksmith.setSectionId(rs.getObject("sectionId") != null ? rs.getInt("sectionId") : null);
        return locksmith;
    }
}

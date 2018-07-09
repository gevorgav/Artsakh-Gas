package dao.mapper;

import Models.ViolationClientHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class violationClientHistoryMapper implements RowMapper<ViolationClientHistory> {

    @Override
    public ViolationClientHistory mapRow(ResultSet resultSet, int i) throws SQLException {
        ViolationClientHistory violationClientHistory = new ViolationClientHistory();
        violationClientHistory.setId(resultSet.getInt("id"));
        violationClientHistory.setViolationId(resultSet.getInt("violationId"));
        violationClientHistory.setClientHistoryId(resultSet.getInt("clientHistoryId"));
        return violationClientHistory;
    }
}

package dao;

import Models.ViolationClientHistory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Repository
public class ViolationClientHistoryDao extends Dao<ViolationClientHistory> {

    @Override
    List<ViolationClientHistory> loadAll() {
        return null;
    }

    @Override
    ViolationClientHistory loadById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(ViolationClientHistory violationClientHistory) {
        Objects.requireNonNull(violationClientHistory);
        String sql = "INSERT INTO violationClientHistory(violationId, clientHistoryId)\n" +
                "VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, violationClientHistory.getViolationId(), violationClientHistory.getClientHistoryId());
        return result == 1;
    }

    @Override
    public boolean update(ViolationClientHistory violationClientHistory) {
        Objects.requireNonNull(violationClientHistory);
        Map namedParameters = new HashMap();
        namedParameters.put("id", violationClientHistory.getId());
        namedParameters.put("violationId" , violationClientHistory.getViolationId());
        namedParameters.put("clientHistoryId", violationClientHistory.getClientHistoryId());
        String sql = "UPDATE violationClientsHistory SET violationId = :violationId, clientHistoryId = :clientHistoryId" +
                "WHERE id = :id";
        int updateCount = namedJdbc.update(sql, namedParameters);
        return updateCount == 1;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }

    public boolean delete(Integer violationId, Integer historyId) {
        Objects.requireNonNull(violationId);
        Objects.requireNonNull(historyId);
        String sql = "DELETE FROM violationClientHistory WHERE violationId = ? AND clientHistoryId = ?";
        int result = jdbcTemplate.update(sql, violationId, historyId);
        return result == 1;
    }
}

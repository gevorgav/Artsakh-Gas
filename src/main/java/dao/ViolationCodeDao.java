package dao;

import Models.ViolationCode;
import dao.mapper.ViolationCodeMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/14/2018.
 */
@Repository
public class ViolationCodeDao extends Dao<ViolationCode> {
    @Override
    public List<ViolationCode> loadAll() {
        try {
            String sql = "SELECT * FROM violationCodes";
            return jdbcTemplate.query(sql, new ViolationCodeMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public ViolationCode loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM violationCodes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ViolationCodeMapper(), id);
    }

    public List<ViolationCode> loadByClientId(Integer clientHistoryId) {
        Objects.requireNonNull(clientHistoryId);
        String sql =    "SELECT violationCodes.id, violationCodes.name, violationCodes.description\n" +
                        "FROM violationCodes\n" +
                        "INNER JOIN violationClientHistory ON violationClientHistory.violationId=violationCodes.id " +
                        "WHERE violationClientHistory.clientHistoryId = ?;";
        return jdbcTemplate.query(sql, new ViolationCodeMapper(), clientHistoryId);
    }

    @Override
    public boolean insert(ViolationCode violationCode) {
        Objects.requireNonNull(violationCode);
        String sql = "INSERT INTO violationCodes(name, description) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, violationCode.getName(), violationCode.getDescription());
        return result == 1;
    }

    @Override
    public boolean update(ViolationCode violationCode) {
        Objects.requireNonNull(violationCode);
        String sql = "UPDATE violationCodes SET name = ?, description = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, violationCode.getName(), violationCode.getDescription(), violationCode.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM violationCodes WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

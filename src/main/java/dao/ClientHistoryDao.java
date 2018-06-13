package dao;

import Models.ClientHistory;
import Models.GRP;
import com.sun.istack.internal.NotNull;
import dao.mapper.ClientHistoryMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public class ClientHistoryDao extends Dao<ClientHistory>{

    /**
     * Method to load all clients history
     */
    public List<ClientHistory> loadAll() {
        try {
            String sql = "SELECT * FROM clientsHistory";
            return jdbcTemplate.query(sql, new ClientHistoryMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Method to load all clients history by given client id
     */
    public List<ClientHistory> loadAllByClientId(Integer clientId) {
        Objects.requireNonNull(clientId);
        try {
            String sql = "SELECT * FROM clientsHistory WHERE clientId = ?";
            return jdbcTemplate.query(sql, new ClientHistoryMapper(), clientId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Method to load ClientHistory by id
     *
     * @param id Id of ClientHistory
     * @return ClientHistory loaded by given id
     */
    public ClientHistory loadById(@NotNull Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM clientsHistory WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ClientHistoryMapper(), id);
    }

    /**
     * Method to load client's last clientHistory
     */
    public ClientHistory loadLastClientHistory(Integer clientId) {
        Objects.requireNonNull(clientId);
        String sql = "SELECT * FROM clientsHistory WHERE clientId = ? ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new ClientHistoryMapper(), clientId);
    }

    /**
     * Method to insert ClientHistory
     */
    public boolean insert(ClientHistory clientHistory) {
        Objects.requireNonNull(clientHistory);
        String sql = "INSERT INTO clientsHistory(clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, codeViolation, stampNumbers, go, jth, jtt, ket, jah, jk, jv, go3, go2, risk)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, clientHistory.getClientId(), clientHistory.getViolationActNumber(), clientHistory.getUpdateDate(),
                clientHistory.getPreviousVisitDate(), clientHistory.getNextVisitDate(), clientHistory.getCodeViolation(),
                clientHistory.getStampNumbers(), clientHistory.getGo(), clientHistory.getJth(), clientHistory.getJtt(),
                clientHistory.getKet(), clientHistory.getJah(), clientHistory.getJk(), clientHistory.getJv(),
                clientHistory.getGo3(), clientHistory.getGo2(), clientHistory.getRisk());
        return result == 1;
    }

    @Override
    boolean update(ClientHistory clientHistory) {
        return false;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }
}

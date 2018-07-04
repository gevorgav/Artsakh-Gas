package dao;

import Models.ClientHistory;
import com.sun.istack.internal.NotNull;
import dao.mapper.ClientHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public class ClientHistoryDao extends Dao<ClientHistory> {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ViolationCodeDao violationCodeDao;

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
        List<ClientHistory> clientHistoryList = jdbcTemplate.query(sql, new ClientHistoryMapper(), clientId);
        if (clientHistoryList.size() < 1) {
            return new ClientHistory();
        }
        return clientHistoryList.get(0);
    }

    /**
     * Method to insert ClientHistory
     */
    public boolean insert(ClientHistory clientHistory) {
        Objects.requireNonNull(clientHistory);
        String sql = "INSERT INTO clientsHistory(clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, codeViolation, stampNumbers, go1, go2, go3,go4,go5,go6," +
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJht, bacakaJtt, bacakaKet, bacakaJah, bacakaJk,bacakaPakan, bacakaJv, risk)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, clientHistory.getClientId(), clientHistory.getViolationActNumber(), clientHistory.getUpdateDate(),
                clientHistory.getPreviousVisitDate(), clientHistory.getNextVisitDate(), clientHistory.getViolationCodeId(), clientHistory.getStampNumbers(),
                clientHistory.getGo1(),clientHistory.getGo2(),clientHistory.getGo3(),clientHistory.getGo4(),clientHistory.getGo5(),clientHistory.getGo6(),
                clientHistory.getBacakaGo1(),clientHistory.getBacakaGo2(),clientHistory.getBacakaGo3(),clientHistory.getBacakaGo4(),clientHistory.getBacakaGo5(),clientHistory.getBacakaGo6(),
                clientHistory.getJth(), clientHistory.getJtt(), clientHistory.getKet(), clientHistory.getJah(), clientHistory.getJk(), clientHistory.getJv(),clientHistory.getPakan(),
                clientHistory.getBacakaJth(), clientHistory.getBacakaJtt(), clientHistory.getBacakaKet(), clientHistory.getBacakaJah(), clientHistory.getBacakaJk(), clientHistory.getBacakaJv(),clientHistory.getBacakaPakan(),
                clientHistory.getRisk());
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

package dao;

import Models.ClientHistory;
import com.sun.istack.internal.NotNull;
import dao.mapper.ClientHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

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
            return null;
        }
        return clientHistoryList.get(0);
    }

    /**
     * Method to insert ClientHistory
     */
    public boolean insert(ClientHistory clientHistory) {
        Objects.requireNonNull(clientHistory);
        String sql = "INSERT INTO clientsHistory(clientId, violationActNumber, visitType, visitDescription, updateDate, previousVisitDate, nextVisitDate, stampNumbers, go1, go2, go3,go4,go5,go6," +
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJth, bacakaJtt, bacakaKet, bacakaJah, bacakaJk,bacakaPakan, bacakaJv, risk, masterId)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, clientHistory.getClientId(), clientHistory.getViolationActNumber(), clientHistory.getVisitType(), clientHistory.getVisitDescription(), new Date(),
                clientHistory.getPreviousVisitDate(), clientHistory.getNextVisitDate(), clientHistory.getStampNumbers(),
                clientHistory.getGo1(),clientHistory.getGo2(),clientHistory.getGo3(),clientHistory.getGo4(),clientHistory.getGo5(),clientHistory.getGo6(),
                clientHistory.getBacakaGo1(),clientHistory.getBacakaGo2(),clientHistory.getBacakaGo3(),clientHistory.getBacakaGo4(),clientHistory.getBacakaGo5(),clientHistory.getBacakaGo6(),
                clientHistory.getJth(), clientHistory.getJtt(), clientHistory.getKet(), clientHistory.getJah(), clientHistory.getJk(), clientHistory.getJv(),clientHistory.getPakan(),
                clientHistory.getBacakaJth(), clientHistory.getBacakaJtt(), clientHistory.getBacakaKet(), clientHistory.getBacakaJah(), clientHistory.getBacakaJk(), clientHistory.getBacakaJv(),clientHistory.getBacakaPakan(),
                clientHistory.getRisk(), clientHistory.getMasterId());
        return result == 1;
    }

    public Integer insertAndReturnId(ClientHistory clientHistory) {
        Objects.requireNonNull(clientHistory);
        String sql = "INSERT INTO clientsHistory(clientId, violationActNumber, visitType, visitDescription, updateDate, previousVisitDate, nextVisitDate, stampNumbers, go1, go2, go3,go4,go5,go6," +
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJth, bacakaJtt, bacakaKet, bacakaJah, bacakaJk,bacakaPakan, bacakaJv, risk, masterId)\n" +
                "VALUES (:clientId, :violationActNumber, :visitType, :visitDescription, :updateDate, :previousVisitDate, :nextVisitDate, :stampNumbers, :go1, :go2, :go3,:go4,:go5,:go6, :bacakaGo1," +
                ":bacakaGo2, :bacakaGo3,:bacakaGo4,:bacakaGo5,:bacakaGo6, :jth, :jtt, :ket, :jah, :jk, :jv,:pakan ,:bacakaJth, :bacakaJtt, :bacakaKet, :bacakaJah, :bacakaJk,:bacakaPakan, :bacakaJv, :risk, :masterId)";
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(clientHistory);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = namedJdbc.update(sql, fileParameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public boolean update(ClientHistory clientHistory) {
        Objects.requireNonNull(clientHistory);
        Map namedParameters = new HashMap();
        namedParameters.put("id", clientHistory.getId());
        namedParameters.put("violationActNumber" , clientHistory.getViolationActNumber());
        namedParameters.put("updateDate", new Date());
        namedParameters.put("previousVisitDate", clientHistory.getPreviousVisitDate());
        namedParameters.put("nextVisitDate", clientHistory.getNextVisitDate());
        namedParameters.put("stampNumbers", clientHistory.getStampNumbers());
        namedParameters.put("visitType", clientHistory.getVisitType());
        namedParameters.put("visitDescription", clientHistory.getVisitDescription());
        namedParameters.put("go1", clientHistory.getGo1());
        namedParameters.put("go2", clientHistory.getGo2());
        namedParameters.put("go3", clientHistory.getGo3());
        namedParameters.put("go4", clientHistory.getGo4());
        namedParameters.put("go5", clientHistory.getGo5());
        namedParameters.put("go6", clientHistory.getGo6());
        namedParameters.put("bacakaGo1", clientHistory.getBacakaGo1());
        namedParameters.put("bacakaGo2", clientHistory.getBacakaGo2());
        namedParameters.put("bacakaGo3", clientHistory.getBacakaGo3());
        namedParameters.put("bacakaGo4", clientHistory.getBacakaGo4());
        namedParameters.put("bacakaGo5", clientHistory.getBacakaGo5());
        namedParameters.put("bacakaGo6", clientHistory.getBacakaGo6());
        namedParameters.put("jth", clientHistory.getJth());
        namedParameters.put("jtt", clientHistory.getJtt());
        namedParameters.put("ket", clientHistory.getKet());
        namedParameters.put("jah", clientHistory.getJah());
        namedParameters.put("jk", clientHistory.getJk());
        namedParameters.put("jv", clientHistory.getJv());
        namedParameters.put("pakan", clientHistory.getPakan());
        namedParameters.put("bacakaJth", clientHistory.getBacakaJth());
        namedParameters.put("bacakaJtt", clientHistory.getBacakaJtt());
        namedParameters.put("bacakaKet", clientHistory.getBacakaKet());
        namedParameters.put("bacakaJah", clientHistory.getBacakaJah());
        namedParameters.put("bacakaJk", clientHistory.getBacakaJk());
        namedParameters.put("bacakaPakan", clientHistory.getBacakaPakan());
        namedParameters.put("bacakaJv", clientHistory.getBacakaJv());
        namedParameters.put("risk", clientHistory.getRisk());
        namedParameters.put("masterId", clientHistory.getMasterId());

        String sql = "UPDATE clientsHistory SET violationActNumber = :violationActNumber, updateDate = :updateDate," +
                " previousVisitDate = :previousVisitDate, nextVisitDate = :nextVisitDate , " +
                "stampNumbers = :stampNumbers, visitType = :visitType,visitDescription = :visitDescription,go1 = :go1, go2 = :go2, go3 = :go3,go4 = :go4,go5 = :go5,go6 = :go6," +
                " bacakaGo1 = :bacakaGo1, bacakaGo2 = :bacakaGo2, bacakaGo3 = :bacakaGo3,bacakaGo4 = :bacakaGo4,bacakaGo5 = :bacakaGo5, " +
                "bacakaGo6 = :bacakaGo6, jth = :jth, jtt = :jtt, ket = :ket, jah = :jah, jk = :jk, jv = :jv,pakan = :pakan ," +
                "bacakaJth = :bacakaJth, bacakaJtt = :bacakaJtt, bacakaKet = :bacakaKet, bacakaJah = :bacakaJah, bacakaJk = :bacakaJk," +
                "bacakaPakan =:bacakaPakan, bacakaJv = :bacakaJv, risk = :risk, masterId = :masterId\n" +
                "WHERE id = :id";

        int updateCount = namedJdbc.update(sql, namedParameters);
        return updateCount == 1;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }
}

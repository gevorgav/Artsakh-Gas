package dao;

import Models.ClientHistory;
import Models.ClientHistoryTmp;
import com.sun.istack.internal.NotNull;
import dao.mapper.ClientHistoryMapper;
import dao.mapper.ClientHistoryTmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by gevorg.avetisyan on 6/13/2018.
 */
@Repository
public class ClientHistoryTmpDao extends Dao<ClientHistoryTmp> {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ViolationCodeDao violationCodeDao;

    /**
     * Method to load all clients history
     */
    public List<ClientHistoryTmp> loadAll() {
        try {
            String sql = "SELECT * FROM clientsHistoryTmp";
            return jdbcTemplate.query(sql, new ClientHistoryTmpMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Method to load all clients history by given client id & region id
     */
    public List<ClientHistory> loadAllByClientId(Integer clientId, Integer regionId) {
        Objects.requireNonNull(clientId);
        try {
            String sql = "SELECT * FROM clientsHistoryTmp WHERE clientId = ? AND regionId = ?";
            return jdbcTemplate.query(sql, new ClientHistoryMapper(), clientId, regionId);
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
    public ClientHistoryTmp loadById(@NotNull Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM clientsHistoryTmp WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ClientHistoryTmpMapper(), id);
    }

    /**
     * Method to load client's last clientHistory
     */
    public ClientHistoryTmp loadLastClientHistory(Integer clientId, Integer regionId) {
        Objects.requireNonNull(clientId);
        String sql = "SELECT * FROM clientsHistoryTmp WHERE clientId = ? AND regionId = ? ORDER BY id DESC LIMIT 1";
        List<ClientHistoryTmp> clientHistoryList = jdbcTemplate.query(sql, new ClientHistoryTmpMapper(), clientId ,regionId);
        if (clientHistoryList.size() < 1) {
            return null;
        }
        return clientHistoryList.get(0);
    }

    /**
     * Method to insert ClientHistory
     */
    public boolean insert(ClientHistoryTmp clientHistory) {
        Objects.requireNonNull(clientHistory);
        String sql = "INSERT INTO clientsHistoryTmp(clientId, clientHistoryId, violationActNumber, visitType, visitDescription, updateDate, previousVisitDate, nextVisitDate, stampNumbers, go1, go2, go3,go4,go5,go6," +
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJth, bacakaJtt, bacakaKet, bacakaJk, bacakaJv, JTLog, risk, masterId, regionId, semiAnnualId)\n" +
                "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, clientHistory.getClientId(), clientHistory.getClientHistoryId(), clientHistory.getViolationActNumber(), clientHistory.getVisitType(), clientHistory.getVisitDescription(), new Date(),
                clientHistory.getPreviousVisitDate(), clientHistory.getNextVisitDate(), clientHistory.getStampNumbers(),
                clientHistory.getGo1(),clientHistory.getGo2(),clientHistory.getGo3(),clientHistory.getGo4(),clientHistory.getGo5(),clientHistory.getGo6(),
                clientHistory.getBacakaGo1(),clientHistory.getBacakaGo2(),clientHistory.getBacakaGo3(),clientHistory.getBacakaGo4(),clientHistory.getBacakaGo5(),clientHistory.getBacakaGo6(),
                clientHistory.getJth(), clientHistory.getJtt(), clientHistory.getKet(), clientHistory.getJah(), clientHistory.getJk(), clientHistory.getJv(),clientHistory.getPakan(),
                clientHistory.getBacakaJth(), clientHistory.getBacakaJtt(), clientHistory.getBacakaKet(), clientHistory.getBacakaJk(), clientHistory.getBacakaJv(),
                clientHistory.getJTLog(), clientHistory.getRisk(), clientHistory.getMasterId(), clientHistory.getRegionId(), clientHistory.getSemiAnnualId());
        return result == 1;
    }

    public Integer insertAndReturnId(ClientHistoryTmp clientHistory) {
        Objects.requireNonNull(clientHistory);
        String sql = "INSERT INTO clientsHistoryTmp(clientId, clientHistoryId, violationActNumber, visitType, visitDescription, updateDate, previousVisitDate, nextVisitDate, stampNumbers, go1, go2, go3,go4,go5,go6," +
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJth, bacakaJtt, bacakaKet, bacakaJk, bacakaJv, JTLog, risk, masterId, regionId, semiAnnualId)\n" +
                "VALUES (:clientId, :clientHistoryId, :violationActNumber, :visitType, :visitDescription, :updateDate, :previousVisitDate, :nextVisitDate, :stampNumbers, :go1, :go2, :go3,:go4,:go5,:go6, :bacakaGo1," +
                ":bacakaGo2, :bacakaGo3,:bacakaGo4,:bacakaGo5,:bacakaGo6, :jth, :jtt, :ket, :jah, :jk, :jv,:pakan ,:bacakaJth, :bacakaJtt, :bacakaKet, :bacakaJk, :bacakaJv,:JTLog, :risk, :masterId, :regionId, :semiAnnualId)";
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(clientHistory);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = namedJdbc.update(sql, fileParameters, keyHolder);
        clientHistory.setId(keyHolder.getKey().intValue());
        return keyHolder.getKey().intValue();
    }

    @Override
    public boolean update(ClientHistoryTmp clientHistory) {
        Objects.requireNonNull(clientHistory);
        Map namedParameters = new HashMap();
        namedParameters.put("id", clientHistory.getId());
        namedParameters.put("clientHistoryId", clientHistory.getClientHistoryId());
        namedParameters.put("violationActNumber" , clientHistory.getViolationActNumber());
        namedParameters.put("updateDate", new Date());
        namedParameters.put("previousVisitDate", clientHistory.getPreviousVisitDate());
        namedParameters.put("nextVisitDate", clientHistory.getNextVisitDate());
        namedParameters.put("semiAnnualId", clientHistory.getSemiAnnualId());
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
        namedParameters.put("bacakaJk", clientHistory.getBacakaJk());
        namedParameters.put("bacakaJv", clientHistory.getBacakaJv());
        namedParameters.put("JTLog", clientHistory.getJTLog());
        namedParameters.put("risk", clientHistory.getRisk());
        namedParameters.put("masterId", clientHistory.getMasterId());
        namedParameters.put("regionId", clientHistory.getRegionId());

        String sql = "UPDATE clientsHistoryTmp SET violationActNumber = :violationActNumber, clientHistoryId = :clientHistoryId, updateDate = :updateDate," +
                " previousVisitDate = :previousVisitDate, nextVisitDate = :nextVisitDate , " +
                "stampNumbers = :stampNumbers, visitType = :visitType,visitDescription = :visitDescription,go1 = :go1, go2 = :go2, go3 = :go3,go4 = :go4,go5 = :go5,go6 = :go6," +
                " bacakaGo1 = :bacakaGo1, bacakaGo2 = :bacakaGo2, bacakaGo3 = :bacakaGo3,bacakaGo4 = :bacakaGo4,bacakaGo5 = :bacakaGo5, " +
                "bacakaGo6 = :bacakaGo6, jth = :jth, jtt = :jtt, ket = :ket, jah = :jah, jk = :jk, jv = :jv,pakan = :pakan ," +
                "bacakaJth = :bacakaJth, bacakaJtt = :bacakaJtt, bacakaKet = :bacakaKet, bacakaJk = :bacakaJk," +
                "bacakaJv = :bacakaJv, JTLog = :JTLog, risk = :risk, masterId = :masterId\n, semiAnnualId = :semiAnnualId " +
                "WHERE id = :id AND regionId = :regionId";

        int updateCount = namedJdbc.update(sql, namedParameters);
        return updateCount == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

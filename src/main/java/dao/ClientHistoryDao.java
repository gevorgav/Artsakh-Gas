package dao;

import Models.ClientHistory;
import com.sun.istack.internal.NotNull;
import dao.mapper.ClientHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

/**
 * Created by gevorg.avetisyan on 6/13/2018.
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

    public List<ClientHistory> loadAllFiltred() {
        try {
            String sql = "select * from clientsHistory WHERE id IN (SELECT MAX(id) AS ids FROM clientsHistory group by clientId,regionId);";
            return jdbcTemplate.query(sql, new ClientHistoryMapper());
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
            String sql = "SELECT * FROM clientsHistory WHERE clientId = ? AND regionId = ?";
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
    public ClientHistory loadById(@NotNull Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM clientsHistory WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ClientHistoryMapper(), id);
    }

    /**
     * Method to load client's last clientHistory
     */
    public ClientHistory loadLastClientHistory(String clientId, Integer regionId, Integer clientIsCompany) {
        Objects.requireNonNull(clientId);
        String sql = "SELECT * FROM clientsHistory WHERE clientId = ? AND isCompany = ? AND regionId = ? ORDER BY id DESC LIMIT 1";
        List<ClientHistory> clientHistoryList = jdbcTemplate.query(sql, new ClientHistoryMapper(), clientId, clientIsCompany ,regionId);
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
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJth, bacakaJtt, bacakaKet, bacakaJk, bacakaJv, JTLog, risk, masterId, regionId, semiAnnualId, userId)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, clientHistory.getClientId(), clientHistory.getViolationActNumber(), clientHistory.getVisitType(), clientHistory.getVisitDescription(), new Date(),
                clientHistory.getPreviousVisitDate(), clientHistory.getNextVisitDate(), clientHistory.getStampNumbers(),
                clientHistory.getGo1(),clientHistory.getGo2(),clientHistory.getGo3(),clientHistory.getGo4(),clientHistory.getGo5(),clientHistory.getGo6(),
                clientHistory.getBacakaGo1(),clientHistory.getBacakaGo2(),clientHistory.getBacakaGo3(),clientHistory.getBacakaGo4(),clientHistory.getBacakaGo5(),clientHistory.getBacakaGo6(),
                clientHistory.getJth(), clientHistory.getJtt(), clientHistory.getKet(), clientHistory.getJah(), clientHistory.getJk(), clientHistory.getJv(),clientHistory.getPakan(),
                clientHistory.getBacakaJth(), clientHistory.getBacakaJtt(), clientHistory.getBacakaKet(), clientHistory.getBacakaJk(), clientHistory.getBacakaJv(),
                clientHistory.getJTLog(), clientHistory.getRisk(), clientHistory.getMasterId(), clientHistory.getRegionId(), clientHistory.getSemiAnnualId(), clientHistory.getUserId());
        return result == 1;
    }

    public int integerToInt(Integer integer){
        return integer == null ? 0 : integer.intValue();
    }

    public void insertBatch(final List<ClientHistory> clientHistories){

        String sql = "INSERT INTO clientsHistory(clientId, violationActNumber, visitType, visitDescription, updateDate, previousVisitDate, nextVisitDate, stampNumbers, go1, go2, go3,go4,go5,go6," +
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJth, bacakaJtt, bacakaKet, bacakaJk, bacakaJv, JTLog, risk, masterId, regionId, semiAnnualId, userId)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ClientHistory customer = clientHistories.get(i);
                ps.setString(1, customer.getClientId());
                ps.setString(2, customer.getViolationActNumber());
                ps.setObject(3, customer.getVisitType(), Types.INTEGER);
                ps.setString(4, customer.getVisitDescription() );
                ps.setDate(5, (java.sql.Date) customer.getUpdateDate());
                ps.setDate(6, (java.sql.Date) customer.getPreviousVisitDate());
                ps.setDate(7, (java.sql.Date) customer.getNextVisitDate());
                ps.setString(8, customer.getStampNumbers() );
                ps.setInt(9, integerToInt(customer.getGo1()));
                ps.setInt(10, integerToInt(customer.getGo2()));
                ps.setInt(11, integerToInt(customer.getGo3()));
                ps.setInt(12, integerToInt(customer.getGo4()));
                ps.setInt(13, integerToInt(customer.getGo5()));
                ps.setInt(14, integerToInt(customer.getGo6()));
                ps.setInt(15, integerToInt(customer.getBacakaGo1()));
                ps.setInt(16, integerToInt(customer.getBacakaGo2()));
                ps.setInt(17, integerToInt(customer.getBacakaGo3()));
                ps.setInt(18, integerToInt(customer.getBacakaGo4()));
                ps.setInt(19, integerToInt(customer.getBacakaGo5()));
                ps.setInt(20, integerToInt(customer.getBacakaGo6()));
                ps.setInt(21, integerToInt(customer.getJth()));
                ps.setInt(22, integerToInt(customer.getJtt()));
                ps.setInt(23, integerToInt(customer.getKet()));
                ps.setInt(24, integerToInt(customer.getJah()));
                ps.setInt(25, integerToInt(customer.getJk()));
                ps.setInt(26, integerToInt(customer.getJv()));
                ps.setInt(27, integerToInt(customer.getPakan()));
                ps.setInt(28, integerToInt(customer.getBacakaJth()));
                ps.setInt(29, integerToInt(customer.getBacakaJtt()));
                ps.setInt(30, integerToInt(customer.getBacakaKet()));
                ps.setInt(31, integerToInt(customer.getBacakaJk()));
                ps.setInt(32, integerToInt(customer.getBacakaJv()));
                ps.setString(33, customer.getJTLog());
                ps.setString(34, customer.getRisk());
                ps.setObject(35, customer.getMasterId(), Types.INTEGER);
                ps.setObject(36, customer.getRegionId(),Types.INTEGER);
                ps.setInt(37, 20182);
                ps.setObject(38, customer.getUserId(), Types.INTEGER);
            }

            @Override
            public int getBatchSize() {
                return clientHistories.size();
            }
        });
    }

    public Integer insertAndReturnId(ClientHistory clientHistory) {
        Objects.requireNonNull(clientHistory);
        clientHistory.setUpdateDate(new Date());
        String sql = "INSERT INTO clientsHistory(clientId, violationActNumber, visitType, visitDescription, updateDate, previousVisitDate, nextVisitDate, stampNumbers, go1, go2, go3,go4,go5,go6," +
                " bacakaGo1, bacakaGo2, bacakaGo3,bacakaGo4,bacakaGo5,bacakaGo6, jth, jtt, ket, jah, jk, jv,pakan ,bacakaJth, bacakaJtt, bacakaKet, bacakaJk, bacakaJv, JTLog, risk, masterId, regionId, semiAnnualId, userId, isCompany)\n" +
                "VALUES (:clientId, :violationActNumber, :visitType, :visitDescription, :updateDate, :previousVisitDate, :nextVisitDate, :stampNumbers, :go1, :go2, :go3,:go4,:go5,:go6, :bacakaGo1," +
                ":bacakaGo2, :bacakaGo3,:bacakaGo4,:bacakaGo5,:bacakaGo6, :jth, :jtt, :ket, :jah, :jk, :jv,:pakan ,:bacakaJth, :bacakaJtt, :bacakaKet, :bacakaJk, :bacakaJv,:JTLog, :risk, :masterId, :regionId, :semiAnnualId, :userId, :isCompany)";
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(clientHistory);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = namedJdbc.update(sql, fileParameters, keyHolder);
        clientHistory.setId(keyHolder.getKey().intValue());
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
        namedParameters.put("userId", clientHistory.getUserId());
        namedParameters.put("isCompany", clientHistory.getIsCompany());

        String sql = "UPDATE clientsHistory SET violationActNumber = :violationActNumber, updateDate = :updateDate," +
                " previousVisitDate = :previousVisitDate, nextVisitDate = :nextVisitDate , " +
                "stampNumbers = :stampNumbers, visitType = :visitType,visitDescription = :visitDescription,go1 = :go1, go2 = :go2, go3 = :go3,go4 = :go4,go5 = :go5,go6 = :go6," +
                " bacakaGo1 = :bacakaGo1, bacakaGo2 = :bacakaGo2, bacakaGo3 = :bacakaGo3,bacakaGo4 = :bacakaGo4,bacakaGo5 = :bacakaGo5, " +
                "bacakaGo6 = :bacakaGo6, jth = :jth, jtt = :jtt, ket = :ket, jah = :jah, jk = :jk, jv = :jv,pakan = :pakan ," +
                "bacakaJth = :bacakaJth, bacakaJtt = :bacakaJtt, bacakaKet = :bacakaKet, bacakaJk = :bacakaJk," +
                "bacakaJv = :bacakaJv, JTLog = :JTLog, risk = :risk, masterId = :masterId\n, semiAnnualId = :semiAnnualId, userId = :userId " +
                "WHERE id = :id AND regionId = :regionId AND isCompany = :isCompany";

        int updateCount = namedJdbc.update(sql, namedParameters);
        return updateCount == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public Integer getVisitedCountByMonth(Integer regionId, Integer semiAnnualId, Integer month, boolean isCompany) {
        String sql = "SELECT COUNT(DISTINCT clientId) FROM clientsHistory\n" +
            "  LEFT JOIN clients ON clients.id = clientsHistory.clientId\n" +
            "WHERE semiAnnualId = ? AND clientsHistory.regionId = ? AND MONTH(previousVisitDate) = ?  AND visitType = 1 AND clients.isCompany = ? AND clients.isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, month, isCompany);
    }

    public Integer getVisitedCountByMonthAndSection(Integer regionId, Integer semiAnnualId, Integer month, Integer sectionId, boolean isCompany) {
        String sql = "SELECT COUNT(DISTINCT clientId) \n" +
            "FROM clientsHistory \n" +
            "LEFT JOIN clients ON clients.id = clientsHistory.clientId \n" +
            "WHERE clientsHistory.semiAnnualId = ? AND clientsHistory.regionId = ? AND MONTH(previousVisitDate) = ? AND clients.sectionId = ? AND visitType = 1 AND isCompany = ? AND clients.isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, month, sectionId, isCompany);
    }

    public Integer getVisitedCountBySemiAnnualAndSection(Integer regionId, Integer semiAnnualId, Integer sectionId, Integer visitType, boolean isCompany) {
        String sql = "SELECT COUNT(DISTINCT clientId)\n" +
            "FROM clientsHistory\n" +
            "LEFT JOIN clients ON clients.id = clientsHistory.clientId\n" +
            "WHERE clientsHistory.semiAnnualId = ? AND clientsHistory.regionId = ? AND clients.sectionId = ? AND visitType = ? AND isCompany = ? AND isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, sectionId, visitType, isCompany);
    }

    public Integer getVisitedCountBySemiAnnual(Integer regionId, Integer semiAnnualId, Integer visitType, boolean isCompany) {
        String sql = "SELECT COUNT(DISTINCT clientId) FROM clientsHistory LEFT JOIN clients ON clientsHistory.clientId = clients.id WHERE semiAnnualId = ? AND clients.regionId = ? AND visitType = ? AND isCompany = ? AND isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, visitType, isCompany);
    }

    public Integer getViolationCodesCountByMonthAndSection(Integer regionId, Integer semiAnnualId, Integer month, Integer sectionId, boolean isCompany) {
        String sql = "SELECT COUNT(violationClientHistory.id)\n" +
            "FROM violationClientHistory\n" +
            "LEFT JOIN clientsHistory ON clientsHistory.id = violationClientHistory.clientHistoryId\n" +
            "LEFT JOIN clients ON clients.id = clientsHistory.clientId\n" +
            "WHERE clientsHistory.semiAnnualId = ? AND clientsHistory.regionId = ? AND MONTH(previousVisitDate) = ? AND clients.sectionId = ? AND isCompany = ? AND isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, month, sectionId, isCompany);
    }


    public Integer getViolationCodesCountByMonth(Integer regionId, Integer semiAnnualId, Integer month, boolean isCompany) {
        String sql = "SELECT COUNT(violationClientHistory.id)\n" +
            "FROM violationClientHistory\n" +
            "LEFT JOIN clientsHistory ON clientsHistory.id = violationClientHistory.clientHistoryId\n" +
            "LEFT JOIN clients ON clientsHistory.clientId = clients.id\n" +
            "WHERE clientsHistory.semiAnnualId = ? AND clientsHistory.regionId = ? AND MONTH(previousVisitDate) = ? AND isCompany = ? AND clients.isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, month, isCompany);
    }

    public Integer getViolationCodesCountBySemiAnnualAndSection(Integer regionId, Integer semiAnnualId, Integer sectionId, boolean isCompany) {
        String sql =     "SELECT COUNT(violationClientHistory.id)\n" +
            "FROM violationClientHistory\n" +
            "LEFT JOIN clientsHistory ON clientsHistory.id = violationClientHistory.clientHistoryId\n" +
            "LEFT JOIN clients ON clients.id = clientsHistory.clientId\n" +
            "WHERE clientsHistory.semiAnnualId = ? AND clientsHistory.regionId = ? AND clients.sectionId = ? AND isCompany = ? AND isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, sectionId, isCompany);
    }

    public Integer getViolationCodesCountBySemiAnnual(Integer regionId, Integer semiAnnualId, boolean isCompany) {
        String sql =     "SELECT COUNT(violationClientHistory.id)\n" +
            "FROM violationClientHistory\n" +
            "LEFT JOIN clientsHistory ON clientsHistory.id = violationClientHistory.clientHistoryId\n" +
            "LEFT JOIN clients ON clientsHistory.clientId = clients.id\n" +
            "WHERE clientsHistory.semiAnnualId = ? AND clientsHistory.regionId = ? AND isCompany = ? AND clients.regionId = ? AND clients.isDeleted = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, semiAnnualId, regionId, isCompany, regionId);
    }

    public boolean moveHistoryToSemiAnnualByRegionId(@NotNull Integer semiAnnualId, @NotNull Integer regionId){
        Map namedParameters = new HashMap();
        namedParameters.put("regionId", regionId);
        namedParameters.put("semiAnnualId", semiAnnualId);
        String query = "INSERT INTO clientsHistory (clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, semiAnnualId)" +
            "                           SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId" +
            "                           FROM clientsHistory" +
            "                           WHERE id IN (SELECT MAX(id)" +
            "                                        FROM clientsHistory" +
            "                                        WHERE regionId = :regionId" +
            "                                        GROUP BY clientId);";
        int updateCount = namedJdbc.update(query, namedParameters);
        return updateCount > 0;
    }

    public boolean moveHistoryToSemiAnnualForAllRegions(@NotNull Integer semiAnnualId){ //TODO GA
        Map namedParameters = new HashMap();
        namedParameters.put("semiAnnualId", semiAnnualId);
        String query = "INSERT INTO clientsHistory (clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, semiAnnualId, isCompany)\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "              FROM (SELECT *\n" +
                "                     FROM clientsHistory\n" +
                "                    WHERE regionId = 1 AND isCompany = 1) s\n" +
                "               GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 1 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "               FROM (SELECT *\n" +
                "                     FROM clientsHistory\n" +
                "                     WHERE regionId = 2 AND isCompany = 1) s\n" +
                "               GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 2 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 3 AND isCompany = 1) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 3 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 4 AND isCompany = 1) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 4 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 5 AND isCompany = 1) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 5 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 6 AND isCompany = 1) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 6 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                     WHERE regionId = 7 AND isCompany = 1) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 7 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 8 AND isCompany = 1) s\n" +
                "                GROUP BY s.clientId)\n" +
                "   UNION ALL\n" +
                "   SELECT clientId, violationActNumber, updateDate, previousVisitDate, nextVisitDate, stampNumbers, risk, pakan, jah, bacakaJth, jth, bacakaJtt, jtt, bacakaJv, jv, bacakaJk, jk, bacakaKet, ket, bacakaGo4, go4, bacakaGo3, go3, bacakaGo2, go2, bacakaGo1, go1, go5, bacakaGo5, go6, bacakaGo6, JTLog, masterId, visitDescription, regionId, userId, :semiAnnualId, isCompany\n" +
                "   FROM clientsHistory\n" +
                "   WHERE id IN (SELECT MAX(id)\n" +
                "                FROM (SELECT *\n" +
                "                      FROM clientsHistory\n" +
                "                      WHERE regionId = 8 AND isCompany = 0) s\n" +
                "                GROUP BY s.clientId);";
        int updateCount = namedJdbc.update(query, namedParameters);
        return updateCount > 0;
    }


}

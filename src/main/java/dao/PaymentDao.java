package dao;

import Models.Payment;
import Models.PaymentReport;
import dao.mapper.PaymentMapper;
import dao.mapper.PaymentReportMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by gevorg.avetisyan on 6/13/2018.
 */
@Repository
public class PaymentDao extends Dao<Payment> {
    @Override
    public List<Payment> loadAll() {
        try {
            String sql = "SELECT * FROM payment";
            return jdbcTemplate.query(sql, new PaymentMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    public List<Payment> paymentsForExportBySemiAnnual(Integer semiAnnualId){
        Objects.requireNonNull(semiAnnualId);
        String sql = "SELECT\n" +
                "  *\n" +
                "FROM payment\n" +
                "WHERE id IN (SELECT MAX(id)\n" +
                "             FROM payment\n" +
                "             WHERE semiAnnualId = ?" +
                "             GROUP BY regionId + clientId) AND  debt - pay > 0.0;";
        return jdbcTemplate.query(sql, new PaymentMapper(), semiAnnualId);
    }

    @Override
    public Payment loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM payment WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PaymentMapper(), id);
    }

    @Override
    public boolean insert(Payment payment) {

        Objects.requireNonNull(payment);
        String sql = "INSERT INTO payment(clientId, clientHistoryTmpId, fullName, regionId, city, street, home, pay, debt, semiAnnualId, updatedDate, userId, bankId, isCompany) " +
                "VALUES (:clientId, :clientHistoryTmpId, :fullName, :regionId, :city, :street, :home, :pay, :debt, :semiAnnualId, :updatedDate, :userId, :bankId, :company)";
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(payment);
        return namedJdbc.update(sql, fileParameters) == 1;
    }

    public void insertBatch(List<Payment> payments) {
        String sql = "INSERT INTO payment(clientId, clientHistoryTmpId, fullName, regionId, city, street, home, pay, debt, semiAnnualId, updatedDate, userId, bankId, isCompany) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int[] updateCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, payments.get(i).getClientId());
                ps.setInt(2, payments.get(i).getClientHistoryTmpId());
                ps.setString(3, payments.get(i).getFullName());
                ps.setInt(4, payments.get(i).getRegionId());
                ps.setString(5, payments.get(i).getCity());
                ps.setString(6, payments.get(i).getStreet());
                ps.setString(7, payments.get(i).getHome());
                ps.setDouble(8, payments.get(i).getPay());
                ps.setDouble(9, payments.get(i).getDebt());
                ps.setInt(10, payments.get(i).getSemiAnnualId());
                ps.setDate(11, Objects.nonNull(payments.get(i).getUpdatedDate()) ? new java.sql.Date(payments.get(i).getUpdatedDate().getTime()) : null);
                ps.setInt(12, payments.get(i).getUserId());
                ps.setInt(13, payments.get(i).getBankId());
                ps.setInt(14, payments.get(i).getCompany() ? 1:0);
            }

            @Override
            public int getBatchSize() {
                return payments.size();
            }
        });

    }

    public Integer insertByLine(String payment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Integer result = namedJdbc.update(payment, null, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public boolean uptadeBankId(Integer paymentId ,Integer bankId){
        Map namedParameters = new HashMap();
        namedParameters.put("id" , paymentId);
        namedParameters.put("bankId", bankId);
        String sql = "UPDATE payment SET bankId = :bankId  WHERE id = :id";

        int updateCount = namedJdbc.update(sql, namedParameters);
        return updateCount == 1;
    }

    @Override
    public boolean update(Payment payment) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public Payment loadLastPayment(String clientId, Integer regionId, Integer semiAnnualId, Integer isCompany) {
        Objects.requireNonNull(clientId);
        String sql = "SELECT * FROM payment WHERE clientId = ? AND regionId = ? AND isCompany = ? AND semiAnnualId = ? ORDER BY id DESC LIMIT 1";
        List<Payment> paymentList = jdbcTemplate.query(sql, new PaymentMapper(), clientId , regionId, isCompany, semiAnnualId);
        if (paymentList.size() < 1) {
            return null;
        }
        return paymentList.get(0);
    }

    public Boolean updatePayment(Payment payment) {
        Objects.requireNonNull(payment);
        Map namedParameters = new HashMap();
        namedParameters.put("id", payment.getId());
        namedParameters.put("clientId", payment.getClientId());

        String sql = "UPDATE payment SET violationActNumber = :violationActNumber, updateDate = :updateDate," +
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

    public List<PaymentReport> loadPaymentsBySemiAnnualId(Integer semiAnnualId) {
        Objects.requireNonNull(semiAnnualId);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        Set<Integer> ids;
        int semi = semiAnnualId % 10;
        if(semi == 1){
            ids = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        } else {
            ids = new HashSet<>(Arrays.asList(7, 8, 9, 10, 11, 12));
        }
        namedParameters.addValue("months", ids);
        namedParameters.addValue("yearId", semiAnnualId/10);
        String sql = "SELECT regionId, bankId, SUM(pay) as pay FROM payment WHERE YEAR(updatedDate) = :yearId AND  MONTH(updatedDate) IN (:months) GROUP BY regionId, bankId";
        List<PaymentReport> paymentList = namedJdbc.query(sql, namedParameters, new PaymentReportMapper());
        return paymentList;
    }

    public List<PaymentReport> loadPaymentsByMonthId(Integer semiAnnualId, Integer monthId) {
        Objects.requireNonNull(semiAnnualId);
        Objects.requireNonNull(monthId);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("monthId", monthId%100);
        namedParameters.addValue("yearId", semiAnnualId/10);
        String sql = "SELECT regionId, bankId, SUM(pay) as pay FROM payment WHERE YEAR(updatedDate) = :yearId AND  MONTH(updatedDate) = :monthId GROUP BY regionId, bankId";
        List<PaymentReport> paymentList = namedJdbc.query(sql, namedParameters, new PaymentReportMapper());
        return paymentList;
    }

    public List<PaymentReport> loadPaymentsByDate() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        LocalDate date = LocalDate.now();
        LocalDate currentDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth() - 1);
        namedParameters.addValue("monthId", currentDate.getMonthValue());
        namedParameters.addValue("yearId", currentDate.getYear());
        namedParameters.addValue("dayId", currentDate.getDayOfMonth());
        String sql = "SELECT regionId, bankId, SUM(pay) as pay FROM payment WHERE YEAR(updatedDate) = :yearId AND  MONTH(updatedDate) = :monthId AND DAY(updatedDate) = :dayId GROUP BY regionId, bankId";
        List<PaymentReport> paymentList = namedJdbc.query(sql, namedParameters, new PaymentReportMapper());
        return paymentList;
    }

    public List<Payment> loadAllLastItems(){
        try {
            String sql = "SELECT * FROM payment WHERE id IN (SELECT MAX(id)FROM payment GROUP BY clientId, regionId, semiAnnualId)";
            return jdbcTemplate.query(sql, new PaymentMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    public List<PaymentReport> loaddebtsBySemiAnnualId(Integer semiAnnualId) {
        Objects.requireNonNull(semiAnnualId);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        Set<Integer> ids;
        int semi = semiAnnualId % 10;
        if(semi == 1){
            ids = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        } else {
            ids = new HashSet<>(Arrays.asList(7, 8, 9, 10, 11, 12));
        }
        namedParameters.addValue("months", ids);
        namedParameters.addValue("yearId", semiAnnualId/10);
        String sql = "select regionId, SUM(balance) from payment WHERE  YEAR(updatedDate) = :yearId AND  MONTH(updatedDate) IN (:months) AND id IN (select max(id) from payment GROUP BY regionId, clientId) GROUP BY regionId";
        List<PaymentReport> paymentList = namedJdbc.query(sql, namedParameters, new PaymentReportMapper());
        return paymentList;
    }
}

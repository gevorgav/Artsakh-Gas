package dao;

import Models.Payment;
import dao.mapper.PaymentMapper;
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
                "             WHERE semiAnnualId = ? " +
                "             GROUP BY regionId + clientId) AND  debt > 0.0;";
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
        String sql = "INSERT INTO payment(clientId, clientHistoryTmpId, fullName, regionId, city, street, home, pay, debt, semiAnnualId, updatedDate, userId, bankId) " +
                "VALUES (:clientId, :clientHistoryTmpId, :fullName, :regionId, :city, :street, :home, :pay, :debt, :semiAnnualId, :updatedDate, :userId, :bankId)";
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(payment);
        return namedJdbc.update(sql, fileParameters) == 1;
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

    public Payment loadLastPayment(String clientId, Integer regionId, Integer semiAnnualId) {
        Objects.requireNonNull(clientId);
        String sql = "SELECT * FROM payment WHERE clientId = ? AND regionId = ? AND semiAnnualId = ? ORDER BY id DESC LIMIT 1";
        List<Payment> paymentList = jdbcTemplate.query(sql, new PaymentMapper(), clientId , regionId, semiAnnualId);
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
}

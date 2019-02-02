package dao.mapper;

import Models.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by gevorg.avetisyan on 6/13/2018.
 */
public class PaymentMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
        Payment payment = new Payment();
        payment.setId(resultSet.getInt("id"));
        payment.setClientId(resultSet.getString("clientId"));
        payment.setClientHistoryTmpId(resultSet.getInt("clientHistoryTmpId"));
        payment.setFullName(resultSet.getString("fullName"));
        payment.setRegionId(resultSet.getInt("regionId"));
        payment.setCity(resultSet.getString("city"));
        payment.setStreet(resultSet.getString("street"));
        payment.setHome(resultSet.getString("home"));
        payment.setSemiAnnualId(resultSet.getInt("semiAnnualId"));
        payment.setDebt(resultSet.getDouble("debt"));
        payment.setPay(resultSet.getDouble("pay"));
        payment.setBalance(resultSet.getDouble("balance"));
        payment.setBankId(resultSet.getInt("bankId"));
        payment.setUpdatedDate(resultSet.getDate("updatedDate"));
        payment.setUserId(resultSet.getInt("userId"));
        payment.setCompany((resultSet.getObject("isCompany") != null &&  resultSet.getInt("isCompany") == 1) ? true : false);
        return payment;
    }
}

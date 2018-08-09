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
        payment.setFirstName(resultSet.getString("firstName"));
        payment.setLastName(resultSet.getString("lastName"));
        payment.setMiddleName(resultSet.getString("middleName"));
        payment.setRegionId(resultSet.getInt("regionId"));
        payment.setCityId(resultSet.getInt("cityId"));
        payment.setStreetId(resultSet.getInt("streetId"));
        payment.setSemiAnnualId(resultSet.getInt("semiAnnualId"));
        payment.setDebt(resultSet.getDouble("debt"));
        payment.setPay(resultSet.getDouble("pay"));
        payment.setBalance(resultSet.getDouble("balance"));
        return payment;
    }
}

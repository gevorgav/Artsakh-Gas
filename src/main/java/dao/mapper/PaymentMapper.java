package dao.mapper;

import Models.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
        Payment payment = new Payment();
        payment.setId(resultSet.getInt("id"));
        payment.setClientId(resultSet.getString("clientId"));
        payment.setClientHistryTmpId(resultSet.getInt("clientHistryTmpId"));
        payment.setFirstName(resultSet.getString("firstName"));
        payment.setLastName(resultSet.getString("lastName"));
        payment.setMiddleName(resultSet.getString("middleName"));
        payment.setBalance(resultSet.getDouble("balance"));
        payment.setPay(resultSet.getDouble("pay"));
        return payment;
    }
}

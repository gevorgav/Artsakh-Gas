package dao.mapper;

import Models.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankMapper implements RowMapper<Bank> {

    @Override
    public Bank mapRow(ResultSet resultSet, int i) throws SQLException {
        Bank bank = new Bank();
        bank.setId(resultSet.getInt("id"));
        bank.setName(resultSet.getString("name"));
        return bank;
    }
}

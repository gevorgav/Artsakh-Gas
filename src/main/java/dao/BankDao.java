package dao;

import Models.Bank;
import dao.mapper.BankMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class BankDao extends Dao<Bank> {
    @Override
    public List<Bank> loadAll() {
        try {
            String sql = "SELECT * FROM banks;";
            return jdbcTemplate.query(sql, new BankMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Bank loadById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(Bank bank) {
        return false;
    }

    @Override
    public boolean update(Bank bank) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

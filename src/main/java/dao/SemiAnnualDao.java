package dao;

import Models.SemiAnnual;
import dao.mapper.SemiAnnualMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
@Repository
public class SemiAnnualDao extends Dao<SemiAnnual> {
    @Override
    public List<SemiAnnual> loadAll() {
        try {
            String sql = "SELECT * FROM semiAnnual";
            return jdbcTemplate.query(
                    sql, new SemiAnnualMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public SemiAnnual loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM semiAnnual WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new SemiAnnualMapper(), id);
    }

    @Override
    boolean insert(SemiAnnual semiAnnual) {
        return false;
    }

    @Override
    boolean update(SemiAnnual semiAnnual) {
        return false;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }
}

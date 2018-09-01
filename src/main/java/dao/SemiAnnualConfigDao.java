package dao;

import Models.SemiAnnualConfig;
import dao.mapper.SemiAnnualConfigMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class SemiAnnualConfigDao extends Dao<SemiAnnualConfig> {

    public SemiAnnualConfig load() {
        try {
            String sql = "  SELECT\n" +
                    "    semiAnnualId,\n" +
                    "    sA.name AS semiAnnualName\n" +
                    "  FROM semiAnnualConfig a\n" +
                    "    INNER JOIN semiAnnual sA ON a.semiAnnualId = sA.id LIMIT 1;";
            return jdbcTemplate.queryForObject(
                    sql, new SemiAnnualConfigMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SemiAnnualConfig> loadAll() {
        return null;
    }

    @Override
    public SemiAnnualConfig loadById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(SemiAnnualConfig semiAnnualConfig) {
        return false;
    }

    @Override
    public boolean update(SemiAnnualConfig semiAnnualConfig) {
        return false;
    }

    public boolean update(Integer semiAnnualConfig) {
        Objects.requireNonNull(semiAnnualConfig);
        String sql = "UPDATE semiAnnualConfig SET semiAnnualId = ?;";
        int result = jdbcTemplate.update(sql, semiAnnualConfig);
        return result == 1;
    }



    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

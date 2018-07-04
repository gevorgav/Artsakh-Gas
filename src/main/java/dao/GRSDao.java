package dao;

import Models.GRS;
import dao.mapper.GRSMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
@Repository
public class GRSDao extends Dao<GRS> {
    @Override
    public List<GRS> loadAll() {
        try {
            String sql = "SELECT * FROM grs";
            return jdbcTemplate.query(
                    sql, new GRSMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public GRS loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM grs WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new GRSMapper(), id);
    }

    @Override
    public boolean insert(GRS grs) {
        Objects.requireNonNull(grs);
        String sql = "INSERT INTO grs(name) VALUES (?)";
        int result = jdbcTemplate.update(sql, grs.getName());
        return result == 1;
    }

    @Override
    public boolean update(GRS grs) {
        Objects.requireNonNull(grs);
        String sql = "UPDATE grs SET name = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, grs.getName(), grs.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM grs WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

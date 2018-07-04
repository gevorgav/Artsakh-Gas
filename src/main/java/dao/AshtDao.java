package dao;

import Models.Asht;
import dao.mapper.AshtMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
@Repository
public class AshtDao extends Dao<Asht> {
    @Override
    public List<Asht> loadAll() {
        try {
            String sql = "SELECT * FROM asht;";
            return jdbcTemplate.query(sql, new AshtMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Asht loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM asht WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new AshtMapper(), id);
    }

    @Override
    public boolean insert(Asht asht) {
        Objects.requireNonNull(asht);
        String sql = "INSERT INTO asht(name, regionId) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, asht.getName(), asht.getRegionId());
        return result == 1;
    }

    @Override
    public boolean update(Asht asht) {
        Objects.requireNonNull(asht);
        String sql = "UPDATE asht SET name = ?, regionId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, asht.getName(), asht.getRegionId(), asht.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM asht WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

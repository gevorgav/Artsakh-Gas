package dao;

import Models.Street;
import dao.mapper.StreetMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/14/2018.
 */
@Repository
public class StreetDao extends Dao<Street> {
    @Override
    public List<Street> loadAll() {
        try {
            String sql = "SELECT * FROM street;";
            return jdbcTemplate.query(sql, new StreetMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Street loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM street WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new StreetMapper(), id);
    }

    @Override
    public boolean insert(Street street) {
        Objects.requireNonNull(street);
        String sql = "INSERT INTO street(name, cityId) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, street.getName(), street.getCityId());
        return result == 1;
    }

    @Override
    public boolean update(Street street) {
        Objects.requireNonNull(street);
        String sql = "UPDATE street SET name = ?, cityId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, street.getName(), street.getCityId(), street.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM street WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

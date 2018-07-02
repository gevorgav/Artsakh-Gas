package dao;

import Models.Type;
import dao.mapper.TypeMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/28/2018.
 */
@Repository
public class TypeDao extends Dao<Type> {
    @Override
    public List<Type> loadAll() {
        try {
            String sql = "SELECT * FROM type;";
            return jdbcTemplate.query(sql, new TypeMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Type loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM type WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new TypeMapper(), id);
    }

    @Override
    public boolean insert(Type type) {
        Objects.requireNonNull(type);
        String sql = "INSERT INTO type(name) VALUES (?)";
        int result = jdbcTemplate.update(sql, type.getName());
        return result == 1;
    }

    @Override
    public boolean update(Type type) {
        Objects.requireNonNull(type);
        String sql = "UPDATE type SET name = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, type.getName(), type.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM type WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

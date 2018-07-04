package dao;

import Core.Models.Region;
import dao.mapper.RegionMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class RegionDao extends Dao<Region>{

    @Override
    public List<Region> loadAll() {
        try {
            String sql = "SELECT * FROM region";
            return jdbcTemplate.query(
                    sql, new RegionMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    public Region loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM region WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new RegionMapper(), id);
    }

    @Override
    public boolean insert(Region region) {
        Objects.requireNonNull(region);
        String sql = "INSERT INTO region(name) VALUES (?)";
        int result = jdbcTemplate.update(sql, region.getName());
        return result == 1;
    }

    @Override
    public boolean update(Region region) {
        Objects.requireNonNull(region);
        String sql = "UPDATE region SET name = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, region.getName(), region.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM region WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }

}

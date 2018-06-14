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
    boolean insert(Region region) {
        return false;
    }

    @Override
    boolean update(Region region) {
        return false;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }

}

package dao;

import Core.Models.Region;
import dao.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class RegionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Region> findAll() {
        try {
            String sql = "SELECT * FROM region;";
            return jdbcTemplate.query(
                    sql, new RegionMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

}

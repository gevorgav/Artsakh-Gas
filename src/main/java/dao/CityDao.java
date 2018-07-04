package dao;

import Core.Models.City;
import dao.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public class CityDao extends Dao<City>{

    public List<City> loadAll() {
        try {
            String sql = "SELECT * FROM city;";
            return jdbcTemplate.query(sql, new CityMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    public City loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM city WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new CityMapper(), id);
    }

    @Override
    public boolean insert(City city) {
        Objects.requireNonNull(city);
        String sql = "INSERT INTO city(name, regionId) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, city.getName(), city.getRegionId());
        return result == 1;
    }

    @Override
    public boolean update(City city) {
        Objects.requireNonNull(city);
        String sql = "UPDATE city SET name = ?, regionId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, city.getName(), city.getRegionId(), city.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM city WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }

}

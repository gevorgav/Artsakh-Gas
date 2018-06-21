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
    boolean insert(City city) {
        return false;
    }

    @Override
    boolean update(City city) {
        return false;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }

}

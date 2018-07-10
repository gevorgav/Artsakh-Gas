package dao;

import Models.Asht;
import Models.PriceList;
import dao.mapper.AshtMapper;
import dao.mapper.PriceListMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 7/4/2018.
 */
@Repository
public class PriceListDao extends Dao<PriceList> {
    @Override
    public List<PriceList> loadAll() {
        try {
            String sql = "SELECT * FROM priceList;";
            return jdbcTemplate.query(sql, new PriceListMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public PriceList loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM priceList WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new PriceListMapper(), id);
    }

    @Override
    public boolean insert(PriceList priceList) {
        Objects.requireNonNull(priceList);
        String sql = "INSERT INTO asht(name, regionId) VALUES (?, ?)";
//        int result = jdbcTemplate.update(sql, priceList.getName(), priceList.getRegionId());
        return true;
    }

    @Override
    public boolean update(PriceList priceList) {
        Objects.requireNonNull(priceList);
        String sql = "UPDATE priceList SET name = ?, price = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, priceList.getName(), priceList.getPrice(), priceList.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
//        String sql = "DELETE FROM priceList WHERE id = ?";
//        int result = jdbcTemplate.update(sql, id);
        return true;
    }
}

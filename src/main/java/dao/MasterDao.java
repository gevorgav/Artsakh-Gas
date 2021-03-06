package dao;

import Models.Master;
import dao.mapper.MasterMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public class MasterDao extends Dao<Master> {

    public List<Master> loadAll() {
        try {
            String sql = "SELECT * FROM master;";
            return jdbcTemplate.query(sql, new MasterMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    public Master loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM master WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new MasterMapper(), id);
    }

    @Override
    public boolean insert(Master master) {
        Objects.requireNonNull(master);
        String sql = "INSERT INTO master(firstName, lastName, ashtId, subSectionId, regionId, sectionId) VALUES (?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, master.getFirstName(), master.getLastName(), master.getAshtId(), master.getSubSectionId(), master.getRegionId(), master.getSectionId());
        return result == 1;
    }

    @Override
    public boolean update(Master master) {
        Objects.requireNonNull(master);
        String sql = "UPDATE master SET firstName = ?, lastName = ?, ashtId = ?, subSectionId = ?, regionId = ?, sectionId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, master.getFirstName(), master.getLastName(), master.getAshtId(), master.getSubSectionId(), master.getRegionId(), master.getSectionId(), master.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM master WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }

}

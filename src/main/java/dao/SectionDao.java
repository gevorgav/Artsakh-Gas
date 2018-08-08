package dao;

import Models.Section;
import dao.mapper.SectionMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/27/2018.
 */
@Repository
public class SectionDao extends Dao<Section> {
    @Override
    public List<Section> loadAll() {
        try {
            String sql = "SELECT * FROM section;";
            return jdbcTemplate.query(sql, new SectionMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Section loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM section WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SectionMapper(), id);
    }

    @Override
    public boolean insert(Section section) {
        Objects.requireNonNull(section);
        String sql = "INSERT INTO section(name, regionId) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, section.getName(), section.getRegionId());
        return result == 1;
    }

    @Override
    public boolean update(Section section) {
        Objects.requireNonNull(section);
        String sql = "UPDATE section SET name = ?, regionId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, section.getName(), section.getRegionId(), section.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM section WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

package dao;

import Models.SubSection;
import dao.mapper.SubSectionMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/27/2018.
 */
@Repository
public class SubSectionDao extends Dao<SubSection> {
    @Override
    public List<SubSection> loadAll() {
        try {
            String sql = "SELECT * FROM subSection;";
            return jdbcTemplate.query(sql, new SubSectionMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public SubSection loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM subSection WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SubSectionMapper(), id);
    }

    @Override
    public boolean insert(SubSection subSection) {
        Objects.requireNonNull(subSection);
        String sql = "INSERT INTO subSection(name, sectionId) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, subSection.getName(), subSection.getSectionId());
        return result == 1;
    }

    @Override
    public boolean update(SubSection subSection) {
        Objects.requireNonNull(subSection);
        String sql = "UPDATE subSection SET name = ?, sectionId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, subSection.getName(), subSection.getSectionId(), subSection.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM subSection WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

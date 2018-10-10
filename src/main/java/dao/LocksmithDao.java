package dao;

import Models.Locksmith;
import dao.mapper.LocksmithMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 7/27/2018.
 */
@Repository
public class LocksmithDao extends Dao<Locksmith>  {
    @Override
    public List<Locksmith> loadAll() {
        try {
            String sql = "SELECT * FROM locksmith;";
            return jdbcTemplate.query(sql, new LocksmithMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Locksmith loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM locksmith WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new LocksmithMapper(), id);
    }

    @Override
    public boolean insert(Locksmith locksmith) {
        Objects.requireNonNull(locksmith);
        String sql = "INSERT INTO locksmith(firstName, lastName, ashtId, regionId, sectionId) VALUES (?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, locksmith.getFirstName(), locksmith.getLastName(), locksmith.getAshtId(), locksmith.getRegionId(), locksmith.getSectionId());
        return result == 1;
    }

    @Override
    public boolean update(Locksmith locksmith) {
        Objects.requireNonNull(locksmith);
        String sql = "UPDATE locksmith SET firstName = ?, lastName = ?, ashtId = ?, regionId = ?, sectionId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, locksmith.getFirstName(), locksmith.getLastName(), locksmith.getAshtId(), locksmith.getRegionId(), locksmith.getSectionId(), locksmith.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM locksmith WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

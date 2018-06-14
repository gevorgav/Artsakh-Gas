package dao;

import Models.GRP;
import dao.mapper.GRPMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public class GRPDao extends Dao<GRP> {

    public List<GRP> loadAll() {
        try {
            String sql = "SELECT * FROM grp;";
            return jdbcTemplate.query(sql, new GRPMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    public GRP loadById(Integer id) {
        Objects.requireNonNull(id);
        String sql = "SELECT * FROM grp WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new GRPMapper(), id);
    }

    public boolean insert(GRP grp) {
        Objects.requireNonNull(grp);
        String sql = "INSERT INTO grp(name, cityId) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, grp.getName(), grp.getCityId());
        return result == 1;
    }

    public boolean update(GRP grp){
        Objects.requireNonNull(grp);
        String sql = "UPDATE grp SET name = ?, cityId = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, grp.getName(), grp.getCityId(), grp.getId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        Objects.requireNonNull(id);
        String sql = "DELETE FROM grp WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result == 1;
    }
}

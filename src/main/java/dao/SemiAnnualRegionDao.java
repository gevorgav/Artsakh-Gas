package dao;

import Models.SemiAnnualRegion;
import dao.mapper.SemiAnnualRegionMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
@Repository
public class SemiAnnualRegionDao extends Dao<SemiAnnualRegion> {
    @Override
    public List<SemiAnnualRegion> loadAll() {
        try {
            String sql = "SELECT" +
                    "  a.id," +
                    "  regionId," +
                    "  semiAnnualId," +
                    "  r.name  AS regionName," +
                    "  sA.name AS semiAnnualName" +
                    "  FROM semiAnnualRegion a INNER JOIN region r ON a.regionId = r.id" +
                    "  INNER JOIN semiAnnual sA ON a.semiAnnualId = sA.id";
            return jdbcTemplate.query(
                    sql, new SemiAnnualRegionMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public SemiAnnualRegion loadById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(SemiAnnualRegion semiAnnualRegion) {
        return false;
    }

    @Override
    public boolean update(SemiAnnualRegion semiAnnualRegion) {
        Objects.requireNonNull(semiAnnualRegion);
        String sql = "UPDATE semiAnnualRegion SET semiAnnualId = ? WHERE regionId  = ?";
        int result = jdbcTemplate.update(sql, semiAnnualRegion.getSemiAnnualId(), semiAnnualRegion.getRegionId());
        return result == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

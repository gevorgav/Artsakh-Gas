package Core.Dao;

import Core.Interface.Dao;
import Core.Models.Region;

import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class RegionDao implements Dao<Region> {

    @Override
    public List<Region> getAll() {
        return null;
    }

    @Override
    public Region getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(Region item) {
        return false;
    }

    @Override
    public boolean update(Region item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

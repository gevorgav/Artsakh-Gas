package Core.Dao;

import Core.Interface.Dao;
import Core.Models.Quarter;

import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class QuarterDao implements Dao<Quarter> {

    @Override
    public List<Quarter> getAll() {
        return null;
    }

    @Override
    public Quarter getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(Quarter item) {
        return false;
    }

    @Override
    public boolean update(Quarter item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

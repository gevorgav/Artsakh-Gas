package Core.Dao;

import Core.Interface.Dao;
import Core.Models.City;

import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class CityDao implements Dao<City> {

    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public City getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(City item) {
        return false;
    }

    @Override
    public boolean update(City item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

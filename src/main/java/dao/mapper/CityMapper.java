package dao.mapper;

import Core.Models.City;
import dao.RegionDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
public class CityMapper  implements RowMapper<City> {

    private RegionDao regionDao;

    @Override
    public City mapRow(ResultSet resultSet, int i) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt("id"));
        city.setName(resultSet.getString("name"));
        city.setRegion(regionDao != null && resultSet.getObject("regionid") != null ? regionDao.loadById(resultSet.getInt("regionid")) : null);
        return city;
    }

    public CityMapper(RegionDao regionDao) {
        this.regionDao = regionDao;
    }
}

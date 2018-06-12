package Core.Dao;

import Core.Interface.Dao;
import Core.Models.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class CountryDao implements Dao<Country> {

    @Override
    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM Country";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Country country = new Country();
                country.setId(res.getInt("id"));
                country.setName(res.getString("name"));
                country.setLat(res.getString("lat"));
                country.setLng(res.getString("lng"));
                country.setName_ENG(res.getString("name_ENG"));
                countries.add(country);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return countries;
    }

    @Override
    public Country getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(Country item) {
        return false;
    }

    @Override
    public boolean update(Country item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

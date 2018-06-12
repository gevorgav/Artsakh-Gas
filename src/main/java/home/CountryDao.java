package home;


import Core.Interface.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gev on 15.03.2017.
 */
public class CountryDao implements Dao<Country> {

    public List<Country> getAll() {
        List<Country> Countrys = new ArrayList<>();
        String sql = "SELECT * FROM Country";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while ( rs.next() ) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                country.setName_ENG(rs.getString("name_ENG"));

                Countrys.add(country);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return Countrys;
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

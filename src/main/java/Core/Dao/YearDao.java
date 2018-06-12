package Core.Dao;

import Core.Interface.Dao;
import Core.Models.Year;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class YearDao implements Dao<Year> {
    @Override
    public List<Year> getAll() {
        List<Year> years = new ArrayList<>();
        String sql = "SELECT * FROM Year";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while ( rs.next() ) {
                Year year = new Year();
                year.setId(rs.getInt("id"));
                year.setYear(rs.getInt("year"));
                years.add(year);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return years;

    }

    @Override
    public Year getById(Integer id) {

        String sql = "SELECT * FROM Year WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Year year = new Year();
                year.setId(rs.getInt("id"));
                year.setYear(rs.getInt("year"));
                return year;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public boolean insert(Year item) {
        String sql = "INSERT INTO Year(year) VALUES(?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, item.getYear());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Year item) {
        String sql = "UPDATE Year SET year = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, item.getYear());
            pstmt.setInt(2, item.getId());

            // update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM Year WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}

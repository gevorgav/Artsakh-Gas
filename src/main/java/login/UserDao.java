package login;

import Core.Interface.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 30.04.2017.
 */
public class UserDao implements Dao<User> {


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                User user = new User();
                user.setId(res.getInt("id"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                users.add(user);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return users;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(User item) {
        return false;
    }

    @Override
    public boolean update(User item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}

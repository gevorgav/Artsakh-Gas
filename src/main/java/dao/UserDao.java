package dao;

import dao.mapper.UserMapper;
import login.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDao extends Dao<User> {

    @Override
    public List<User> loadAll() {
        try {
            String sql = "SELECT * FROM users;";
            return jdbcTemplate.query(sql, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    User loadById(Integer id) {
        return null;
    }

    @Override
    boolean insert(User user) {
        return false;
    }

    @Override
    boolean update(User user) {
        return false;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }
}

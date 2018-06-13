package dao;

import Models.GRP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public abstract class Dao<T> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    abstract List<T> loadAll();

    abstract T loadById(Integer id);

    abstract boolean insert(T t);

    abstract boolean update(T t);

    abstract boolean delete(Integer id);
}

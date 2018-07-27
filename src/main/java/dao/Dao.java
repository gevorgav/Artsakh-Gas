package dao;

import Models.GRP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by astghik.mamunc on 6/13/2018.
 */
@Repository
public abstract class Dao<T> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate namedJdbc;

    public abstract List<T> loadAll();

    public abstract T loadById(Integer id);

    public abstract boolean insert(T t);

    public abstract boolean update(T t);

    public abstract boolean delete(Integer id);
}

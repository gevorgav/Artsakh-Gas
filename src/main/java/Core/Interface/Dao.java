package Core.Interface;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public interface Dao<T> {

    public List<T> getAll();

    public T getById(Integer id);

    public boolean insert(T item);

    public boolean update(T item);

    public boolean delete(Integer id);

    default Connection connect() {
        // SQLite connection string
        Properties prop = new Properties();
        String url = "";
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = prop.getProperty("db.name");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

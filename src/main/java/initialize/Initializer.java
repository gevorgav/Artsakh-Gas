package initialize;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Initializer implements ServletContextListener {

	public Initializer() {

	}

	private static ComboPooledDataSource dataSource;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Properties prop = new Properties();
		String url = "";
		try {

			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
			prop.load(input);
			url = prop.getProperty("db.name");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try  {
			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(url);
//			if (conn != null) {
//				DatabaseMetaData meta = conn.getMetaData();
//				System.out.println("The driver name is " + meta.getDriverName());
//				System.out.println("A new database has been created.");
//			}

		}  catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		dataSource.close();

	}

	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
}

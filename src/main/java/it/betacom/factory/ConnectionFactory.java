package it.betacom.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("db.properties"));
			String url = properties.getProperty("jdbc.url");
			String user = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException | IOException exception) {
			exception.printStackTrace();
		}
		return connection;
	}
}

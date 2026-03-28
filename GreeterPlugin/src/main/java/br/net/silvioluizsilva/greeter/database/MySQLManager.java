package br.net.silvioluizsilva.greeter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager {

    private Connection connection;

    private final String host;
    private final String database;
    private final String username;
    private final String password;
    private final int port;

    public MySQLManager(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() {

        try {

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to MySQL!");

        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL!");
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {

        if (connection != null) {
            try {
                connection.close();
                System.out.println("MySQL connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
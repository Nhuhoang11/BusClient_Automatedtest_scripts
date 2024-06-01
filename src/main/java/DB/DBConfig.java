package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static final String DB_ENGINE = "jdbc:postgresql://";
    private static final String DB_HOST = "aws-0-ap-southeast-1.pooler.supabase.com";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "postgres";
    private static final String DB_USER = "postgres.steoxvkhrrqvvdazfqfh";
    private static final String DB_PASSWORD = "Quanquan2311.";

    public static Connection getConnection() throws SQLException {
        // URL connection
        String dbUrl = DB_ENGINE + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        // Connect to DB
        Connection connection = DriverManager.getConnection(dbUrl, DB_USER, DB_PASSWORD);
        System.out.println("Connected to the database successfully!");
        return connection;
    }
}

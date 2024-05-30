package DB;

import java.sql.*;

public class GetAllEmailOfTableParent {
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

    public static String getFirstParentEmail() {
        String query = "SELECT * FROM \"public\".\"Parent\"";
        String parentEmail = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process query results
            if (resultSet.next()) {
                parentEmail = resultSet.getString("email");
            } else {
                System.out.println("No driver found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentEmail;
    }

    public static boolean isEmailInDatabase(String email) {
        String query = "SELECT COUNT(*) FROM \"public\".\"Parent\" WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}

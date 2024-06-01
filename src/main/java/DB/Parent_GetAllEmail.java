package DB;

import java.sql.*;

import static DB.DBConfig.getConnection;

public class Parent_GetAllEmail {
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

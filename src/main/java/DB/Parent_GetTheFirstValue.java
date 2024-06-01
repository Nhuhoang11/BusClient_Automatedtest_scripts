package DB;

import java.sql.*;

import static DB.DBConfig.getConnection;

public class Parent_GetTheFirstValue {

    public static String getFirstParentEmail() {
        String query = "SELECT * FROM \"public\".\"Parent\" WHERE device IS NOT NULL LIMIT 1";
        String parentEmail = null;
//        Boolean parentDevice = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả truy vấn
            if (resultSet.next()) {
//                String parentId = resultSet.getString("id");
//                String userName = resultSet.getString("user_name");
                parentEmail = resultSet.getString("email");
//                String avatar = resultSet.getString("avatar");
//                String dateOfBirth = resultSet.getString("date_of_birth");
//                String language = resultSet.getString("language");
//                String createdAt = resultSet.getString("created_at");
//                String updatedAt = resultSet.getString("updated_at");
//                String device = resultSet.getString("device");

//                System.out.println("Parent ID: " + parentId);
//                System.out.println("User Name: " + userName);
//                System.out.println("Parent Email: " + parentEmail);
//                System.out.println("Avatar: " + avatar);
//                System.out.println("Date of Birth: " + dateOfBirth);
//                System.out.println("Language: " + language);
//                System.out.println("Created At: " + createdAt);
//                System.out.println("Updated At: " + updatedAt);
//                System.out.println("Device: " + device);
            } else {
                System.out.println("No driver found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentEmail;
    }

//    public static void main(String[] args) {
//        getFirstParentEmail();
//    }
}

package DB;

import java.sql.*;

import static DB.DBConfig.getConnection;

public class Parent_GetDataWithEmail {
    public static ResultSet getParentData(String email) throws SQLException {
        String query = "SELECT user_name, email, date_of_birth, language " +
                "FROM \"public\".\"Parent\" " +
                "WHERE email = ?";

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        System.out.println("Get data from email...");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


//    public static void getParentDataByEmail(String email) throws SQLException {
//        String query = "SELECT name, date_of_birth FROM \"public\".\"Parent\" " +
//                "WHERE email = ?";
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            // Đặt giá trị cho tham số email
//            preparedStatement.setString(1, email);
//            // Thực thi truy vấn
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            // Xử lý kết quả truy vấn
//            while (resultSet.next()) {
//                // Dữ liệu từ bảng Parent
//                String parentId = resultSet.getString("id");
//                String userName = resultSet.getString("user_name");
//                String parentEmail = resultSet.getString("email");
////                String avatar = resultSet.getString("avatar");
//                String dateOfBirth = resultSet.getString("date_of_birth");
////                String language = resultSet.getString("language");
////                String createdAt = resultSet.getString("created_at");
////                String updatedAt = resultSet.getString("updated_at");
////                String device = resultSet.getString("device");
//
//                // In ra kết quả
//                System.out.println("Parent ID: " + parentId);
//                System.out.println("User Name: " + userName);
//                System.out.println("Parent Email: " + parentEmail);
////                System.out.println("Avatar: " + avatar);
//                System.out.println("Date of Birth: " + dateOfBirth);
////                System.out.println("Language: " + language);
////                System.out.println("Created At: " + createdAt);
////                System.out.println("Updated At: " + updatedAt);
////                System.out.println("Device: " + device);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}

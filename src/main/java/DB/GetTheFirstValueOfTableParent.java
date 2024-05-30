package DB;

import java.sql.*;

public class GetTheFirstValueOfTableParent {
    private static final String DB_ENGINE = "jdbc:postgresql://";
    private static final String DB_HOST = "aws-0-ap-southeast-1.pooler.supabase.com";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "postgres";
    private static final String DB_USER = "postgres.steoxvkhrrqvvdazfqfh";
    private static final String DB_PASSWORD = "Quanquan2311.";

    public static Connection getConnection() throws SQLException {
        // Xây dựng URL kết nối
        String dbUrl = DB_ENGINE + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        // Tạo kết nối đến cơ sở dữ liệu
        Connection connection = DriverManager.getConnection(dbUrl, DB_USER, DB_PASSWORD);
        System.out.println("Connected to the database successfully!");
        return connection;
    }

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

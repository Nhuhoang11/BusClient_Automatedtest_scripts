package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection_Driver {

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

    public static void getDriverByEmail(String email) {
        String query = "SELECT * FROM \"public\".\"Driver\" WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Đặt giá trị cho tham số email
            preparedStatement.setString(1, email);

            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả truy vấn
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String userName = resultSet.getString("user_name");
                String avatar = resultSet.getString("avatar");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String companyId = resultSet.getString("company_id");
                String license = resultSet.getString("license");
                String busNumber = resultSet.getString("bus_number");
                String language = resultSet.getString("language");
                String createdAt = resultSet.getString("created_at");
                String updatedAt = resultSet.getString("updated_at");
                String phoneNumber = resultSet.getString("phone_number");
                boolean isVerify = resultSet.getBoolean("is_verify");

                System.out.println("Driver ID: " + id);
                System.out.println("User Name: " + userName);
                System.out.println("Email: " + email);
                System.out.println("Avatar: " + avatar);
                System.out.println("Date of Birth: " + dateOfBirth);
                System.out.println("Company ID: " + companyId);
                System.out.println("License: " + license);
                System.out.println("Bus Number: " + busNumber);
                System.out.println("Language: " + language);
                System.out.println("Created At: " + createdAt);
                System.out.println("Updated At: " + updatedAt);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Is Verified: " + isVerify);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String email = "vapade3443@mfyax.com";
        getDriverByEmail(email);
    }
}

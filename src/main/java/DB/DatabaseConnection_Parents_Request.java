package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection_Parents_Request {

    private static final String DB_ENGINE = "jdbc:postgresql://";
    private static final String DB_HOST = "aws-0-ap-southeast-1.pooler.supabase.com";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "postgres";
    private static final String DB_USER = "postgres.steoxvkhrrqvvdazfqfh";
    private static final String DB_PASSWORD = "Quanquan2311.";

    public static Connection getConnection() throws SQLException {
        try {
            // Nạp driver PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Xây dựng URL kết nối
        String dbUrl = DB_ENGINE + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        // Tạo kết nối đến cơ sở dữ liệu
        Connection connection = DriverManager.getConnection(dbUrl, DB_USER, DB_PASSWORD);
        System.out.println("Connected to the database successfully!");
        return connection;
    }

    public static void getParentAndRequestByEmail(String email) {
        String query = "SELECT p.*, r.* FROM \"public\".\"Parent\" p JOIN \"public\".\"Request\" r ON p.id = r.parent_id WHERE p.email = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Đặt giá trị cho tham số email
            preparedStatement.setString(1, email);

            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả truy vấn
            while (resultSet.next()) {
                // Dữ liệu từ bảng Parent
                String parentId = resultSet.getString("id");
                String userName = resultSet.getString("user_name");
                String parentEmail = resultSet.getString("email");
                String avatar = resultSet.getString("avatar");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String language = resultSet.getString("language");
                String createdAt = resultSet.getString("created_at");
                String updatedAt = resultSet.getString("updated_at");
                String device = resultSet.getString("device");

                // Dữ liệu từ bảng Request
                String requestId = resultSet.getString(13); // chỉ mục bắt đầu từ 1, 13 tương ứng với cột thứ 13 trong kết quả truy vấn
                String status = resultSet.getString("status");
                String adminId = resultSet.getString("admin_id");
                String address = resultSet.getString("address");
                String templateId = resultSet.getString("template_id");
                String requestEmail = resultSet.getString(18); // 18 tương ứng với cột email của bảng Request
                String zipPostal = resultSet.getString("zip_postal");
                String note = resultSet.getString("note");
                String requestCreatedAt = resultSet.getString(23); // tương ứng với cột created_at của bảng Request
                String requestUpdatedAt = resultSet.getString("updated_at");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phone_number");
                String shippingBy = resultSet.getString("shipping_by");
                String shippingCode = resultSet.getString("shipping_code");

                // In ra kết quả
                System.out.println("Parent ID: " + parentId);
                System.out.println("User Name: " + userName);
                System.out.println("Parent Email: " + parentEmail);
                System.out.println("Avatar: " + avatar);
                System.out.println("Date of Birth: " + dateOfBirth);
                System.out.println("Language: " + language);
                System.out.println("Created At: " + createdAt);
                System.out.println("Updated At: " + updatedAt);
                System.out.println("Device: " + device);

                System.out.println("Request ID: " + requestId);
                System.out.println("Status: " + status);
                System.out.println("Admin ID: " + adminId);
                System.out.println("Address: " + address);
                System.out.println("Template ID: " + templateId);
                System.out.println("Request Email: " + requestEmail);
                System.out.println("Zip/Postal: " + zipPostal);
                System.out.println("Note: " + note);
                System.out.println("Request Created At: " + requestCreatedAt);
                System.out.println("Request Updated At: " + requestUpdatedAt);
                System.out.println("Name: " + name);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Shipping By: " + shippingBy);
                System.out.println("Shipping Code: " + shippingCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String email = "1obmd@fthcapital.com";
        getParentAndRequestByEmail(email);
    }
}

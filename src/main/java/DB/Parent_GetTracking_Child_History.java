package DB;

import java.sql.*;

import static DB.DBConfig.getConnection;

public class Parent_GetTracking_Child_History {
    public static void getTrackingHistory(String email) {
        String query = "SELECT p.*, c.*, h.* " +
                "FROM \"public\".\"Parent\" p " +
                "JOIN \"public\".\"Child\" c ON p.id = c.parent_id " +
                "JOIN \"public\".\"History\" h ON c.id = h.child_id " +
                "WHERE p.email = ?";

        int count = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Đặt giá trị cho tham số email
            preparedStatement.setString(1, email);

            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả truy vấn
            while (resultSet.next()) {
                count++;
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

                // Dữ liệu từ bảng Child
                String childId = resultSet.getString(13); // chỉ mục bắt đầu từ 1, 13 tương ứng với cột thứ 13 trong kết quả truy vấn
                String parentIdFromChild = resultSet.getString("parent_id");
                String childName = resultSet.getString("child_name");
                String nickname = resultSet.getString("nickname");
                String gender = resultSet.getString("gender");
                String childDateOfBirth = resultSet.getString("date_of_birth");
                String cardNumber = resultSet.getString("card_number");
                String childAvatar = resultSet.getString("avatar");
                String name = resultSet.getString("name");
                String childCreatedAt = resultSet.getString("created_at");
                String childUpdatedAt = resultSet.getString("updated_at");
                String schoolId = resultSet.getString("school_id");

                // Dữ liệu từ bảng History
                String historyId = resultSet.getString(25); // chỉ mục bắt đầu từ 1, 25 tương ứng với cột thứ 25 trong kết quả truy vấn
                String driverId = resultSet.getString("driver_id");
                String childIdFromHistory = resultSet.getString("child_id");
                String historyCreatedAt = resultSet.getString("created_at");
                String historyUpdatedAt = resultSet.getString("updated_at");
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");

                // In ra kết quả
                System.out.println("Parent ID: " + parentId);
                System.out.println("User Name: " + userName);
                System.out.println("Parent Email: " + parentEmail);
//                System.out.println("Avatar: " + avatar);
//                System.out.println("Date of Birth: " + dateOfBirth);
//                System.out.println("Language: " + language);
//                System.out.println("Created At: " + createdAt);
//                System.out.println("Updated At: " + updatedAt);
//                System.out.println("Device: " + device);

                System.out.println("Child ID: " + childId);
//                System.out.println("Parent ID from Child: " + parentIdFromChild);
//                System.out.println("Child Name: " + childName);
//                System.out.println("Nickname: " + nickname);
//                System.out.println("Gender: " + gender);
//                System.out.println("Child Date of Birth: " + childDateOfBirth);
                System.out.println("Card Number: " + cardNumber);
//                System.out.println("Child Avatar: " + childAvatar);
                System.out.println("Name: " + name);
//                System.out.println("Child Created At: " + childCreatedAt);
//                System.out.println("Child Updated At: " + childUpdatedAt);
//                System.out.println("School ID: " + schoolId);

//                System.out.println("History ID: " + historyId);
                System.out.println("Driver ID: " + driverId);
//                System.out.println("Child ID from History: " + childIdFromHistory);
                System.out.println("History Created At: " + historyCreatedAt);
                System.out.println("History Updated At: " + historyUpdatedAt);
                System.out.println("Location: " + location);
//                System.out.println("Type: " + type);
                System.out.println("----------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int countTrackingOfChild(String email) {
        String query = "SELECT p.*, c.*, h.* " +
                "FROM \"public\".\"Parent\" p " +
                "JOIN \"public\".\"Child\" c ON p.id = c.parent_id " +
                "JOIN \"public\".\"History\" h ON c.id = h.child_id " +
                "WHERE p.email = ?";

        int count = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Đặt giá trị cho tham số email
            preparedStatement.setString(1, email);

            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả truy vấn
            while (resultSet.next()) {
                count++;
                // Dữ liệu từ bảng Parent
                // Dữ liệu từ bảng Child
                // Dữ liệu từ bảng History

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
